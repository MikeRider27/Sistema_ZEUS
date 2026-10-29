/* =====================================================================
   SISTEMA ZEUS - escritorio (frames.Menu): JDesktopPane con ventanas
   internas, barra de menu, barra de estado y bloqueo por inactividad.
   ===================================================================== */
(function () {
    'use strict';
    const { $, $$ } = Z;
    const desk = $('#desktop');
    const barra = $('#taskbar');
    const ventanas = [];
    let zTop = 10, cascada = 0;

    // ------------------------------- menu ---------------------------------
    let menuAbierto = false;
    $$('.menubar > .m').forEach(m => {
        m.querySelector('.m-titulo').addEventListener('mousedown', e => {
            e.preventDefault();
            const abrir = !m.classList.contains('abierto');
            cerrarMenus();
            if (abrir) { m.classList.add('abierto'); menuAbierto = true; }
        });
        m.addEventListener('mouseenter', () => {
            if (menuAbierto && !m.classList.contains('abierto')) { cerrarMenus(); m.classList.add('abierto'); menuAbierto = true; }
        });
    });
    function cerrarMenus() {
        $$('.menubar .m.abierto').forEach(x => x.classList.remove('abierto'));
        menuAbierto = false;
    }
    document.addEventListener('mousedown', e => { if (!e.target.closest('.menubar')) cerrarMenus(); });

    $$('.menubar .item[data-url]').forEach(it => it.addEventListener('click', e => {
        e.stopPropagation();
        cerrarMenus();
        const url = it.dataset.url;
        if (url.startsWith('#')) return acciones[url.slice(1)]();
        abrir(url, it.dataset.titulo, it.dataset.icono, it);
    }));

    const acciones = {
        bloquear: () => bloquear(),
        maximizar: () => {
            if (document.fullscreenElement) document.exitFullscreen();
            else document.documentElement.requestFullscreen().catch(() => {});
        },
        logout: async () => {
            if (ventanas.length && !(await Z.confirm('Hay ' + ventanas.length + ' formulario(s) abiertos.\n¿Desea salir del Sistema de todas formas?'))) return;
            if (!ventanas.length && !(await Z.confirm('¿Desea salir del Sistema?'))) return;
            $('#frmLogout').submit();
        }
    };

    // ------------------------- ventanas internas --------------------------
    const TAMANOS = {
        '/sis/calculadora': [300, 400], '/sis/calendario': [480, 430], '/sis/pass': [440, 290],
        '/sis/acerca': [600, 360], '/sis/backup': [620, 300], '/mov/stock': [900, 520]
    };
    function abrir(url, titulo, icono, item) {
        const ya = ventanas.find(v => v.url === url);
        if (ya) { activar(ya); if (ya.el.classList.contains('min')) ya.el.classList.remove('min'); return; }

        const el = document.createElement('div');
        el.className = 'iframe-win';
        const W = desk.clientWidth, H = desk.clientHeight;
        const [w0, h0] = TAMANOS[url] || [980, 620];
        const w = Math.min(w0, W - 10), h = Math.min(h0, H - 10);
        const off = (cascada++ % 8) * 24;
        el.style.width = w + 'px';
        el.style.height = h + 'px';
        el.style.left = Math.max(0, Math.min(W - w, 20 + off)) + 'px';
        el.style.top = Math.max(0, Math.min(H - h, 10 + off)) + 'px';
        el.innerHTML = `<div class="t"><img alt=""><span></span>
            <button type="button" class="mn" title="Minimizar">&#8212;</button>
            <button type="button" class="mx" title="Maximizar">&#9633;</button>
            <button type="button" class="x" title="Cerrar">&#10005;</button></div>
            <iframe></iframe><div class="velo"></div>`;
        $('.t img', el).src = '/img/' + (icono || 'logo') + '.png';
        $('.t span', el).textContent = titulo;
        const fr = $('iframe', el);
        fr.title = titulo;
        fr.src = url;
        desk.appendChild(el);

        const tb = document.createElement('button');
        tb.type = 'button';
        tb.innerHTML = '<img alt=""><span></span>';
        $('img', tb).src = '/img/' + (icono || 'logo') + '.png';
        $('span', tb).textContent = titulo;
        barra.appendChild(tb);

        const v = { url, el, fr, tb, item };
        ventanas.push(v);
        if (item) item.classList.add('dis');     // Menu.menX.setEnabled(false)

        el.addEventListener('mousedown', () => activar(v), true);
        fr.addEventListener('load', () => {
            try { fr.contentWindow.addEventListener('mousedown', () => activar(v), true); } catch (e) { /* otro origen */ }
        });
        tb.onclick = () => {
            if (el.classList.contains('min') || !el.classList.contains('activa')) { el.classList.remove('min'); activar(v); }
            else el.classList.add('min');
        };
        $('.mn', el).onclick = e => { e.stopPropagation(); el.classList.add('min'); tb.classList.remove('activa'); };
        $('.mx', el).onclick = e => { e.stopPropagation(); el.classList.toggle('max'); };
        $('.x', el).onclick = e => { e.stopPropagation(); pedirCierre(v); };
        $('.t', el).addEventListener('dblclick', () => el.classList.toggle('max'));
        arrastrable(el, $('.t', el));
        activar(v);
    }

    // la X de la ventana se comporta como el boton Salir del formulario
    function pedirCierre(v) {
        let b = null;
        try { b = v.fr.contentDocument.getElementById('btnExit'); } catch (e) { /* sin acceso */ }
        if (b) b.click(); else cerrarVentana(v);
    }

    function cerrarVentana(v) {
        v.el.remove();
        v.tb.remove();
        if (v.item) v.item.classList.remove('dis');
        ventanas.splice(ventanas.indexOf(v), 1);
        const ult = ventanas[ventanas.length - 1];
        if (ult) activar(ult);
    }

    function activar(v) {
        ventanas.forEach(x => { x.el.classList.remove('activa'); x.tb.classList.remove('activa'); });
        v.el.classList.add('activa');
        v.tb.classList.add('activa');
        v.el.style.zIndex = ++zTop;
    }

    function arrastrable(el, asa) {
        asa.addEventListener('mousedown', e => {
            if (e.target.closest('button') || el.classList.contains('max')) return;
            e.preventDefault();
            const x0 = e.clientX - el.offsetLeft, y0 = e.clientY - el.offsetTop;
            desk.classList.add('arrastrando');
            const mover = ev => {
                el.style.left = Math.max(-el.offsetWidth + 80, Math.min(desk.clientWidth - 80, ev.clientX - x0)) + 'px';
                el.style.top = Math.max(0, Math.min(desk.clientHeight - 26, ev.clientY - y0)) + 'px';
            };
            const soltar = () => {
                desk.classList.remove('arrastrando');
                document.removeEventListener('mousemove', mover);
                document.removeEventListener('mouseup', soltar);
            };
            document.addEventListener('mousemove', mover);
            document.addEventListener('mouseup', soltar);
        });
        // mientras se redimensiona, el iframe no debe capturar el mouse
        el.addEventListener('mousedown', e => {
            const r = el.getBoundingClientRect();
            if (e.clientX > r.right - 18 && e.clientY > r.bottom - 18) {
                desk.classList.add('arrastrando');
                document.addEventListener('mouseup', () => desk.classList.remove('arrastrando'), { once: true });
            }
        });
    }

    // ------------------------- fecha y hora (LabelDate) -------------------
    const DIAS = ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];
    function reloj() {
        const d = new Date();
        $('#lblFecha').textContent = `${DIAS[d.getDay()]} ${d.getDate()} de ${Z.MESES[d.getMonth()]} de ${d.getFullYear()} - `
            + d.toLocaleTimeString('es-PY', { hour: '2-digit', minute: '2-digit' });
    }
    reloj();
    setInterval(reloj, 15000);

    // --------------------- bloqueo (InactivityListener) -------------------
    const minutos = Number(document.body.dataset.bloqueo || 15);
    let ultima = Date.now();
    function actividad() { ultima = Date.now(); }
    ['keydown', 'mousedown', 'mousemove'].forEach(ev => document.addEventListener(ev, actividad, true));
    setInterval(() => { if (minutos > 0 && Date.now() - ultima > minutos * 60000) bloquear(); }, 20000);

    function bloquear() {
        const b = $('#bloqueo');
        if (b.classList.contains('on')) return;
        b.classList.add('on');
        $('#txtDesbloqueo').value = '';
        setTimeout(() => $('#txtDesbloqueo').focus(), 50);
    }
    async function desbloquear() {
        const r = await Z.post('/api/desbloquear', { pass: $('#txtDesbloqueo').value });
        $('#txtDesbloqueo').value = '';
        if (r) { $('#bloqueo').classList.remove('on'); actividad(); }
    }
    $('#btnDesbloquear').onclick = desbloquear;
    $('#txtDesbloqueo').addEventListener('keydown', e => { if (e.key === 'Enter') desbloquear(); });

    window.Escritorio = {
        abrir,
        actividad,
        cerrar(win) {
            const v = ventanas.find(x => x.fr.contentWindow === win);
            if (v) cerrarVentana(v);
        }
    };
})();
