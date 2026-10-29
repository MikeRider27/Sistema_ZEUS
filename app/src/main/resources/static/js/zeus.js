/* =====================================================================
   SISTEMA ZEUS - utilidades de las ventanas internas
   Equivalentes web de: conectarDB (consultas), JOptionPane, TblThread,
   CboThreadGT, ManageButtons, toUpperCaseState, fieldControl...
   ===================================================================== */
(function () {
    'use strict';

    const $ = (s, r = document) => typeof s === 'string' ? r.querySelector(s) : s;
    const $$ = (s, r = document) => Array.from(r.querySelectorAll(s));
    const MESES = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto',
        'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];

    const meta = n => (document.querySelector(`meta[name="${n}"]`) || {}).content;
    let pendientes = 0;

    function cargando(d) {
        pendientes += d;
        let el = $('#zCargando');
        if (!el) {
            el = document.createElement('div');
            el.id = 'zCargando';
            el.className = 'cargando';
            el.textContent = 'Consultando...';
            document.body.appendChild(el);
        }
        el.style.display = pendientes > 0 ? 'block' : 'none';
    }

    // avisa al escritorio que hubo actividad (bloqueo por inactividad)
    function actividad() {
        try { window.parent.Escritorio && window.parent.Escritorio.actividad(); } catch (e) { /* sin escritorio */ }
    }
    ['keydown', 'mousedown'].forEach(ev => document.addEventListener(ev, actividad, true));

    async function req(url, opt) {
        cargando(1);
        try {
            const r = await fetch(url, Object.assign({ credentials: 'same-origin', headers: {} }, opt));
            if (r.redirected && r.url.includes('/login')) {
                window.top.location = '/login';
                return null;
            }
            const ct = r.headers.get('content-type') || '';
            const data = ct.includes('json') ? await r.json() : null;
            if (!r.ok) {
                await Z.msg((data && data.msg) || ('Error ' + r.status), (data && data.titulo) || 'Error', 'error');
                return null;
            }
            return data;
        } catch (e) {
            await Z.msg('No se pudo comunicar con el servidor\n' + e.message, 'Error de conexión', 'error');
            return null;
        } finally {
            cargando(-1);
        }
    }

    function qs(params) {
        const p = new URLSearchParams();
        Object.entries(params || {}).forEach(([k, v]) => { if (v !== null && v !== undefined && v !== '') p.append(k, v); });
        const s = p.toString();
        return s ? '?' + s : '';
    }

    // ------------------------------ dialogos ------------------------------
    function dialogo(texto, titulo, tipo, botones, conInput) {
        return new Promise(resolve => {
            const fondo = document.createElement('div');
            fondo.className = 'dlg-fondo';
            const ico = { info: 'i', warn: '', error: '×', preg: '?' }[tipo] ?? 'i';
            fondo.innerHTML = `<div class="dlg" role="dialog" aria-modal="true">
                <div class="dlg-titulo"></div>
                <div class="dlg-cuerpo"><div class="dlg-icono ${tipo}">${ico}</div><div class="dlg-texto" style="flex:1"></div></div>
                <div class="dlg-botones"></div></div>`;
            $('.dlg-titulo', fondo).textContent = titulo;
            $('.dlg-texto', fondo).textContent = texto;
            let input = null;
            if (conInput) {
                input = document.createElement('input');
                input.type = conInput;
                $('.dlg-texto', fondo).appendChild(input);
            }
            const cerrar = v => { fondo.remove(); document.removeEventListener('keydown', tecla, true); resolve(v); };
            botones.forEach(([txt, val], i) => {
                const b = document.createElement('button');
                b.className = 'btn';
                b.textContent = txt;
                b.onclick = () => cerrar(conInput && val ? input.value : val);
                $('.dlg-botones', fondo).appendChild(b);
                if (i === 0) setTimeout(() => (input || b).focus(), 0);
            });
            const tecla = e => {
                if (e.key === 'Escape' || (e.key === 'Enter' && input)) e.stopImmediatePropagation();
                if (e.key === 'Escape') { e.preventDefault(); cerrar(conInput ? null : botones[botones.length - 1][1]); }
                if (e.key === 'Enter' && input) { e.preventDefault(); cerrar(input.value); }
            };
            document.addEventListener('keydown', tecla, true);
            document.body.appendChild(fondo);
        });
    }

    // ------------------------------ formato -------------------------------
    const fmtNum = v => {
        if (v === null || v === undefined || v === '') return '';
        const n = Number(v);
        if (isNaN(n)) return v;
        return n.toLocaleString('es-PY', { maximumFractionDigits: 2 });
    };
    const fmtFecha = v => {
        if (!v) return '';
        const m = /^(\d{4})-(\d{2})-(\d{2})/.exec(v);
        return m ? `${m[3]}/${m[2]}/${m[1]}` : v;
    };
    const fmtBool = v => v === true ? 'SI' : v === false ? 'NO' : v;
    const FMT = { n: fmtNum, d: fmtFecha, b: fmtBool };

    // ------------------------------- tablas -------------------------------
    /**
     * Llena una JTable. cols: [{c:'columna', f:'n|d|b', cls:'n|c', html:(row)=>Node|string}]
     * opt.onSel(row, i): seleccion de fila; opt.vacio: texto sin datos; opt.claseFila(row)
     */
    function tabla(t, rows, cols, opt = {}) {
        t = $(t);
        const tb = t.tBodies[0] || t.appendChild(document.createElement('tbody'));
        tb.innerHTML = '';
        t._rows = rows;
        t._sel = -1;
        if (!rows || rows.length === 0) {
            tb.innerHTML = `<tr class="vacia"><td colspan="${cols.length}"></td></tr>`;
            tb.firstChild.firstChild.textContent = opt.vacio || 'Sin registros';
            return;
        }
        rows.forEach((r, i) => {
            const tr = document.createElement('tr');
            if (opt.claseFila) { const c = opt.claseFila(r); if (c) tr.className = c; }
            cols.forEach(col => {
                const td = document.createElement('td');
                const cls = col.cls || (col.f === 'n' ? 'n' : col.f === 'b' ? 'c' : '');
                if (cls) td.className = cls;
                if (col.html) {
                    const h = col.html(r, i);
                    if (h instanceof Node) td.appendChild(h); else td.innerHTML = h;
                } else {
                    const v = r[col.c];
                    td.textContent = col.f ? FMT[col.f](v) : (v ?? '');
                    if (col.c === 'est_cod') td.classList.add('est');
                }
                tr.appendChild(td);
            });
            if (opt.onSel) {
                tr.addEventListener('mousedown', e => {
                    if (e.target.closest('input,select,button')) return;
                    $$('tr.sel', tb).forEach(x => x.classList.remove('sel'));
                    tr.classList.add('sel');
                    t._sel = i;
                    opt.onSel(r, i);
                });
            }
            tb.appendChild(tr);
        });
    }

    // ------------------------------- combos -------------------------------
    function combo(sel, rows, placeholder, valor = 'cod', texto = 'des') {
        sel = $(sel);
        const prev = sel.value;
        sel.innerHTML = '';
        if (placeholder !== null && placeholder !== undefined) {
            const o = document.createElement('option');
            o.value = '';
            o.textContent = placeholder;
            sel.appendChild(o);
        }
        (rows || []).forEach(r => {
            const o = document.createElement('option');
            o.value = r[valor];
            o.textContent = r[texto];
            o._row = r;
            sel.appendChild(o);
        });
        sel._rows = rows || [];
        if (prev && $$('option', sel).some(o => o.value === prev)) sel.value = prev;
    }

    // --------------------------- pestañas ---------------------------------
    function tabs(root) {
        root = $(root);
        const btns = $$('.tab-bar button', root);
        const panels = $$('.tab-panel', root);
        const sel = i => {
            btns.forEach((b, j) => b.classList.toggle('activa', i === j));
            panels.forEach((p, j) => p.classList.toggle('activa', i === j));
        };
        btns.forEach((b, i) => b.addEventListener('click', () => { if (!root.classList.contains('bloqueada')) sel(i); }));
        return {
            sel,
            // durante la edicion no se puede cambiar de pestaña (jTabbedPane1.setEnabled(false))
            bloquear: v => { root.classList.toggle('bloqueada', v); btns.forEach(b => b.disabled = v); }
        };
    }

    // ------------------------- filtro de listados -------------------------
    // "Filtrar Por" mes / año / estado como en los listados del legacy
    function filtros(cb) {
        const hoy = new Date();
        const mes = $('#fMes'), anho = $('#fAnho'), est = $('#fEst');
        if (mes) {
            combo(mes, MESES.map((m, i) => ({ cod: i + 1, des: m })), 'Todos');
            mes.value = hoy.getMonth() + 1;
        }
        if (anho) {
            const a = [];
            for (let y = hoy.getFullYear(); y >= hoy.getFullYear() - 5; y--) a.push({ cod: y, des: y });
            combo(anho, a, 'Todos');
            anho.value = hoy.getFullYear();
        }
        [mes, anho, est, $('#fSuc')].forEach(s => s && s.addEventListener('change', cb));
        return () => ({ mes: mes && mes.value, anho: anho && anho.value, est: est && est.value, suc: $('#fSuc') && $('#fSuc').value });
    }

    // ----------------------- ventana de movimiento ------------------------
    /**
     * Comportamiento comun de los movimientos (ManageButtons 1 boot / 2 pressed / 3 editing).
     * cfg: {key, lista:{q, tabla, cols, cod}, det:{q, tabla, cols}, puedeAnular(row),
     *       nuevo(), guardar() -> {cab, det} | null, limpiar(), alSeleccionar(row), acciones:[{btn, puede(row)}]}
     */
    function mov(cfg) {
        const t = tabs('#tabs');
        const b = {
            add: $('#btnAdd'), nul: $('#btnNull'), save: $('#btnSave'), cancel: $('#btnCancel'), exit: $('#btnExit')
        };
        const m = { ban: 0, sel: null, tabs: t };
        const leerFiltros = filtros(() => m.cargarLista());

        m.estado = function (e) {
            const acc = cfg.acciones || [];
            if (e === 1) {
                t.bloquear(false);
                t.sel(1);
                if (b.add) b.add.disabled = !!cfg.sinAgregar;
                [b.nul, b.save, b.cancel].forEach(x => x && (x.disabled = true));
                acc.forEach(a => $(a.btn).disabled = true);
                m.sel = null;
            } else if (e === 2) {
                if (b.add) b.add.disabled = true;
                if (b.nul) b.nul.disabled = !(cfg.puedeAnular ? cfg.puedeAnular(m.sel) : m.sel.est_cod === 'VIG');
                if (b.save) b.save.disabled = true;
                if (b.cancel) b.cancel.disabled = false;
                acc.forEach(a => $(a.btn).disabled = !a.puede(m.sel));
            } else if (e === 3) {
                [b.add, b.nul].forEach(x => x && (x.disabled = true));
                acc.forEach(a => $(a.btn).disabled = true);
                if (b.save) b.save.disabled = false;
                if (b.cancel) b.cancel.disabled = false;
                t.sel(0);
                t.bloquear(true);
            }
        };

        m.cargarLista = async function () {
            const rows = await Z.q(cfg.lista.q, Object.assign(leerFiltros(), cfg.lista.params ? cfg.lista.params() : {}));
            if (!rows) return;
            tabla(cfg.lista.tabla, rows, cfg.lista.cols, {
                onSel: async r => {
                    m.sel = r;
                    m.estado(2);
                    if (cfg.det) {
                        const d = await Z.q(cfg.det.q, { cod: r[cfg.lista.cod] });
                        if (d) tabla(cfg.det.tabla, d, cfg.det.cols, { vacio: 'Sin detalles' });
                    }
                    if (cfg.alSeleccionar) cfg.alSeleccionar(r);
                }
            });
            if (cfg.det) tabla(cfg.det.tabla, [], cfg.det.cols, { vacio: 'Seleccione un registro del listado' });
            m.estado(1);
        };

        m.limpiar = function () {
            m.ban = 0;
            $$('#edicion input:not([type=checkbox]):not([type=radio]), #edicion textarea').forEach(i => i.value = '');
            $$('#edicion input[type=checkbox]').forEach(i => i.checked = false);
            $$('#edicion select').forEach(s => s.selectedIndex = 0);
            if (cfg.limpiar) cfg.limpiar();
        };

        /** ejecuta abm_x en la BD; devuelve el codigo o null */
        m.ejecutar = async function (ban, cod, cab, det, mensaje) {
            const r = await Z.post(`/mov/${cfg.key}/save`, { ban, cod, cab: cab || null, det: det || null });
            if (!r) return null;
            await Z.msg(mensaje || (ban === 1 ? `Registro Guardado con Éxito\nCódigo Nº ${r.cod}` : 'Operación realizada con Éxito'),
                'Mensaje', 'info');
            return r.cod;
        };

        /** pasa a la pestaña Edicion con la bandera indicada */
        m.editar = function (ban) {
            m.ban = ban;
            m.estado(3);
        };

        m.terminar = async function () {
            m.limpiar();
            await m.cargarLista();
        };

        if (b.add) b.add.onclick = async () => {
            m.limpiar();
            m.ban = 1;
            m.estado(3);
            if (cfg.nuevo) await cfg.nuevo();
        };
        if (b.nul) b.nul.onclick = async () => {
            if (!m.sel) return;
            if (await Z.confirm('¿Desea Anular este Registro?')) {
                const ok = await m.ejecutar(3, m.sel[cfg.lista.cod], null, null, 'Registro Anulado con Éxito');
                if (ok !== null) await m.terminar();
            }
        };
        if (b.save) b.save.onclick = async () => {
            const d = await cfg.guardar(m.ban);
            if (!d) return;
            const cod = d.cod !== undefined ? d.cod : (m.ban === 1 ? null : m.sel && m.sel[cfg.lista.cod]);
            const r = await m.ejecutar(m.ban, cod, d.cab, d.det, d.mensaje);
            if (r !== null) await m.terminar();
        };
        if (b.cancel) b.cancel.onclick = async () => {
            if (m.ban && !(await Z.confirm('Hay datos sin guardar, ¿Desea cancelar de todas formas?'))) return;
            await m.terminar();
        };
        if (b.exit) b.exit.onclick = async () => {
            if (m.ban && !(await Z.confirm('Hay datos sin guardar, ¿Desea Salir de todas formas?'))) return;
            Z.salir();
        };
        $('#btnRefrescar') && ($('#btnRefrescar').onclick = () => m.cargarLista());
        m.cargarLista();
        return m;
    }

    // ------------------------------- API ----------------------------------
    const Z = window.Z = {
        $, $$, MESES, fmtNum, fmtFecha, tabla, combo, tabs, filtros, mov,

        get: (url, params) => req(url + qs(params)),
        post: (url, body) => req(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', [meta('_csrf_header')]: meta('_csrf') },
            body: JSON.stringify(body)
        }),
        q: (nombre, params) => req('/q/' + nombre + qs(params)),

        async cargarCombo(sel, nombre, params, placeholder = '-- ELEGIR --') {
            const rows = await Z.q(nombre, params);
            combo(sel, rows || [], placeholder);
            return rows || [];
        },
        /** fila completa de la opcion elegida en un combo */
        filaCombo: sel => { const o = $(sel).selectedOptions[0]; return o && o._row; },

        msg: (texto, titulo = 'Atención', tipo = 'warn') => dialogo(texto, titulo, tipo, [['Aceptar', true]]),
        confirm: (texto, titulo = 'Confirmar') => dialogo(texto, titulo, 'preg', [['Sí', true], ['No', false]]),
        prompt: (texto, titulo = 'Ingrese', tipo = 'text') => dialogo(texto, titulo, 'preg', [['Aceptar', true], ['Cancelar', null]], tipo),

        /** cierra la ventana interna (dispose + Menu.menX.setEnabled(true)) */
        salir() {
            try { window.parent.Escritorio.cerrar(window); } catch (e) { window.close(); }
        },

        /** valida los obligatorios marcados con label.req; devuelve true si esta todo */
        async requeridos(root = document) {
            for (const l of $$('label.req[for]', root)) {
                const el = document.getElementById(l.htmlFor);
                if (el && !el.disabled && String(el.value).trim() === '') {
                    await Z.msg('Debe completar los campos Obligatorios(*) para proceder\nCampo: ' + l.textContent.trim());
                    el.focus();
                    return false;
                }
            }
            return true;
        },

        num: v => { const n = Number(String(v ?? '').replace(/\./g, '').replace(',', '.')); return isNaN(n) ? 0 : n; },
        hoy: () => new Date().toISOString().slice(0, 10)
    };

    // texto en mayusculas mientras se escribe (DB.toUpperCaseState)
    document.addEventListener('input', e => {
        const el = e.target;
        if (el.classList && el.classList.contains('upper')) {
            const p = el.selectionStart;
            el.value = el.value.toUpperCase();
            try { el.setSelectionRange(p, p); } catch (x) { /* tipos sin seleccion */ }
        }
    });
    // solo numeros (DB.fieldControl)
    document.addEventListener('keypress', e => {
        const el = e.target;
        if (el.classList && el.classList.contains('solo-num') && e.key.length === 1 && !/[0-9.,-]/.test(e.key)) e.preventDefault();
    });
    // la tecla ESC cierra la ventana si no esta en edicion
    document.addEventListener('keydown', e => {
        if (e.key === 'Escape' && !document.querySelector('.dlg-fondo')) {
            const b = document.getElementById('btnExit');
            if (b && !b.disabled) b.click();
        }
    });
})();
