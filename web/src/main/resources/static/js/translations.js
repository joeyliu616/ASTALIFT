/**
 * INSPINIA - Responsive Admin Theme
 *
 */
function config($translateProvider) {

    $translateProvider
        .translations('en', {
            //new
            //供应
            SELL_DASHBOARD: '供应管理面板',
            NEW_TASK:'新订单',
            RUNNING_TASK:'运行中的订单',
            FINISHED_TASK:'已完成订单',

            //采购
            BUY_DASHBOARD:'采购管理面板',
            LIST_PRODUCTS:'浏览商品',
            RUNNING_ORDERS:'待收货订单',
            FINISHED_ORDERS:'已完成订单',

            //库存
            STOCK_DASHBOARD:'库存管理面板',

            // Define all menu elements
            DASHBOARD: 'Dashboard',
            GRAPHS: 'Graphs',
            MAILBOX: 'Mailbox',
            WIDGETS: 'Widgets',
            METRICS: 'Metrics',
            FORMS: 'Forms',
            APPVIEWS: 'App views',
            OTHERPAGES: 'Other pages',
            UIELEMENTS: 'UI elements',
            MISCELLANEOUS: 'Miscellaneous',
            GRIDOPTIONS: 'Grid options',
            TABLES: 'Tables',
            COMMERCE: 'E-commerce',
            GALLERY: 'Gallery',
            MENULEVELS: 'Menu levels',
            ANIMATIONS: 'Animations',
            LANDING: 'Landing page',
            LAYOUTS: 'Layouts',

            // Define some custom text
            WELCOME: 'Welcome Amelia',
            MESSAGEINFO: 'You have 42 messages and 6 notifications.',
            SEARCH: 'Search for something...',
            DEMO: 'Internationalization (sometimes shortened to \"I18N , meaning \"I - eighteen letters -N\") is the process of planning and implementing products and services so that they can easily be adapted to specific local languages and cultures, a process called localization . The internationalization process is sometimes called translation or localization enablement .'

        })
        .translations('es', {
            //new
            //供应
            SELL_DASHBOARD: '供应管理面板',
            NEW_TASK:'新订单',
            RUNNING_TASK:'运行中的订单',
            FINISHED_TASK:'已完成订单',

            //采购
            BUY_DASHBOARD:'采购管理面板',
            LIST_PRODUCTS:'浏览商品',
            RUNNING_ORDERS:'待收货订单',
            FINISHED_ORDERS:'已完成订单',

            //库存
            STOCK_DASHBOARD:'库存管理面板',

            // Define all menu elements
            DASHBOARD: 'Salpicadero',
            GRAPHS: 'Gráficos',
            MAILBOX: 'El correo',
            WIDGETS: 'Widgets',
            METRICS: 'Métrica',
            FORMS: 'Formas',
            APPVIEWS: 'Vistas app',
            OTHERPAGES: 'Otras páginas',
            UIELEMENTS: 'UI elements',
            MISCELLANEOUS: 'Misceláneo',
            GRIDOPTIONS: 'Cuadrícula',
            TABLES: 'Tablas',
            COMMERCE: 'E-comercio',
            GALLERY: 'Galería',
            MENULEVELS: 'Niveles de menú',
            ANIMATIONS: 'Animaciones',
            LANDING: 'Página de destino',
            LAYOUTS: 'Esquemas',

            // Define some custom text
            WELCOME: 'Bienvenido Amelia',
            MESSAGEINFO: 'Usted tiene 42 mensajes y 6 notificaciones.',
            SEARCH: 'Busca algo ...',
            DEMO: 'Internacionalización (a veces abreviado como \"I18N, que significa\" I - dieciocho letras N \") es el proceso de planificación e implementación de productos y servicios de manera que se pueden adaptar fácilmente a las lenguas y culturas locales específicas, un proceso llamado localización El proceso de internacionalización. a veces se llama la traducción o la habilitación de localización.'
        })
        .translations('cn', {

            // Define all menu elements
            //供应
            SELL_DASHBOARD: '供应管理面板',
            NEW_TASK:'未处理订单',
            RUNNING_TASK:'运行中订单',
            FINISHED_TASK:'已完成订单',

            //采购
            BUY_DASHBOARD:'采购管理面板',
            LIST_PRODUCTS:'浏览商品',
            RUNNING_ORDERS:'待收货订单',
            FINISHED_ORDERS:'已完成订单',

            //库存
            STOCK_DASHBOARD:'库存管理面板',

            GRAPHS: 'Graphs',
            MAILBOX: 'Mailbox',
            WIDGETS: 'Widgets',
            METRICS: 'Metrics',
            FORMS: 'Forms',
            APPVIEWS: 'App views',
            OTHERPAGES: 'Other pages',
            UIELEMENTS: 'UI elements',
            MISCELLANEOUS: 'Miscellaneous',
            GRIDOPTIONS: 'Grid options',
            TABLES: 'Tables',
            COMMERCE: 'E-commerce',
            GALLERY: 'Gallery',
            MENULEVELS: 'Menu levels',
            ANIMATIONS: 'Animations',
            LANDING: 'Landing page',
            LAYOUTS: 'Layouts',

            // Define some custom text
            WELCOME: 'Welcome Amelia',
            MESSAGEINFO: 'You have 42 messages and 6 notifications.',
            SEARCH: 'Search for something...',
            DEMO: 'Internationalization (sometimes shortened to \"I18N , meaning \"I - eighteen letters -N\") is the process of planning and implementing products and services so that they can easily be adapted to specific local languages and cultures, a process called localization . The internationalization process is sometimes called translation or localization enablement .'

        });

    $translateProvider.preferredLanguage('cn');

}

angular
    .module('inspinia')
    .config(config)
