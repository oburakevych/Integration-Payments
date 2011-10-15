/**
 * @fileoverview Selects tab and subtab on menu bar depending on page url.
 */

/**
 * @class <p>Selects tab and subtab on menu bar depending on page url.</p>
 * @constructor
 *
 * @requires CommonUtils CommonUtils
 * @requires CookieHandler CookieHandler
 * @requires HtmlUtils HtmlUtils
 */
var MenuTabSelector = function() {
};

/**
 * @class <p>Contains navigation related constants.</p>
 * @constructor
 */
MenuTabSelector.constant = function() {
};

MenuTabSelector.constant = {};

MenuTabSelector.constant.ACTIVE = "active";
MenuTabSelector.constant.SHOW = "show";
MenuTabSelector.constant.PREFIX_HEADER_TAB = "header-tab-";

/* For the future use */
MenuTabSelector.constant.PREFIX_FOOTER_TAB = "footer-tab-";
MenuTabSelector.constant.PREFIX_HEADER_SUBTAB = "header-subtab-";
MenuTabSelector.constant.PREFIX_FOOTER_SUBTAB = "footer-subtab-";
MenuTabSelector.constant.PREFIX_HEADER_SUBMENU = "header-subnav-tab-";
MenuTabSelector.constant.PREFIX_FOOTER_SUBMENU = "footer-subnav-tab-";
MenuTabSelector.constant.MENU_TAB_COOKIE_KEY = "MENU_TAB";

/* Menu tabs constants. */
MenuTabSelector.constant.NONE = "no-tab-selected";
MenuTabSelector.constant.HOME = "home";
MenuTabSelector.constant.PROFILE = "profile";
MenuTabSelector.constant.ACCOUNT = "account";
MenuTabSelector.constant.HELP_HELP = "help";

/**
 * Pages URL suffix.
 */
MenuTabSelector.URL_SUFFIX = ".view";

/**
 * @class <p>Contains site navigation URLs.</p>
 * @constructor
 */
MenuTabSelector.url = function() {
};

MenuTabSelector.url = {};

MenuTabSelector.url.HOME_EMPTY = "/";
MenuTabSelector.url.HOME = "/home" + MenuTabSelector.URL_SUFFIX;
MenuTabSelector.url.INDEX = "/index" + MenuTabSelector.URL_SUFFIX;
MenuTabSelector.url.AUTH_LOGIN = "/login" + MenuTabSelector.URL_SUFFIX;
MenuTabSelector.url.AUTH_LOGOUT = "/logout" + MenuTabSelector.URL_SUFFIX;
MenuTabSelector.url.PROFILE = "/profile" + MenuTabSelector.URL_SUFFIX;
MenuTabSelector.url.ACCOUNT = "/account" + MenuTabSelector.URL_SUFFIX;
MenuTabSelector.url.HELP_HELP = "/help" + MenuTabSelector.URL_SUFFIX;

/**
 * @class <p>Contains bindings between URLs and tab to select in navigation bar.<p>
 * @constructor
 */
MenuTabSelector.tab = function() {
};

MenuTabSelector.tab = {};

MenuTabSelector.tab[MenuTabSelector.url.HOME] = MenuTabSelector.constant.HOME;
MenuTabSelector.tab[MenuTabSelector.url.HOME_EMPTY] = MenuTabSelector.constant.HOME;
MenuTabSelector.tab[MenuTabSelector.url.INDEX] = MenuTabSelector.constant.HOME;
MenuTabSelector.tab[MenuTabSelector.url.AUTH_LOGIN] = MenuTabSelector.constant.HOME;
MenuTabSelector.tab[MenuTabSelector.url.AUTH_LOGOUT] = MenuTabSelector.constant.HOME;
MenuTabSelector.tab[MenuTabSelector.url.PROFILE] = MenuTabSelector.constant.PROFILE;
MenuTabSelector.tab[MenuTabSelector.url.ACCOUNT] = MenuTabSelector.constant.ACCOUNT;
MenuTabSelector.tab[MenuTabSelector.url.HELP] = MenuTabSelector.constant.HELP;


/**
 * Selects tab and subtab in navigation bar that corresponds to given URL.
 *
 * @param {String} url URL tab and subTab for which will be selected.
 */
MenuTabSelector.selectTab = function(url) {
	var menuTab = MenuTabSelector.tab[url];

	var headerMenuTab = document.getElementById(MenuTabSelector.constant.PREFIX_HEADER_TAB + menuTab);

	if(!CommonUtils.isNullOrUndefined(headerMenuTab)) {
		EventHandler.setStyle(headerMenuTab, MenuTabSelector.constant.ACTIVE);
	}
};


MenuTabSelector.selectPage = function(pageUri) {
	MenuTabSelector.selectTab(pageUri);
};
