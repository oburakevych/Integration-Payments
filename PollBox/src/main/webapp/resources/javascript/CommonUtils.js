/**
 * @fileoverview Provides commonly used util methods for work assistance.
 */

/**
 * @class <p>Provides commonly used util methods for work assistance.</p>
 * @constructor
 */
var CommonUtils = function() {
};

/**
 * Specifies if page is opened in Microsoft Internet Explorer browser.
 *
 * @return <code>true</code> if page is opened in Microsoft Internet Explorer browser, else - <code>false</code>.
 * @type Boolean
 */
CommonUtils.isBrowserIE = function() {
	return navigator.appName.indexOf("Microsoft Internet Explorer") != -1;
};

/**
 * Specifies if page is opened in Netscape Firefox or Navigator browsers.
 *
 * @return <code>true</code> if page is opened in Netscape Firefox or Navigator browsers. Else - <code>false</code>
 * @type Boolean
 */
CommonUtils.isBrowserFF = function() {
	return navigator.appName.indexOf("Netscape") != -1;
};

/**
 * String that indicates that browser is not supported.
 *
 * @type String
 * @final
 *
 * @private
 */
CommonUtils.ERR_BAD_BROWSER = "Unsupported browser.";

/**
 * Contains element class property name: it differs Microsoft Internet Explorer and Netscape Firefox or Navigator browsers.
 *
 * @type String
 * @final
 */
CommonUtils.CLASS_ATTRIBUTE = CommonUtils.isBrowserIE() ? "className" : (CommonUtils.isBrowserFF() ? "class" : CommonUtils.ERR_BAD_BROWSER);

/**
 * Copies all properties of some object to another object.
 *
 * @param {Object} source object for proprties to be copied from.
 * @param {Object} target object for proprties to be copied to.
 */
CommonUtils.copyObjectProperties = function(source, target) {
	if (CommonUtils.isNullOrUndefined(source)) {
		return;
	}

	for (var property in source) {
		target[property] = source[property];
	}
};

/**
 * Checks if value is null or undefined.
 *
 * @param {Object} value value to check
 *
 * @return <code>true</code> if value is null or undefined, else - <code>false</code>.
 * @type Boolean
 */
CommonUtils.isNullOrUndefined = function(value) {
	return typeof value == "undefined" || value === null;
};

/**
 * Redirects user to url.
 *
 * @param {String} url Url to reditect to.
 */
CommonUtils.redirect = function(url) {
	window.location = url;
};

/**
 * Trows JavaScript exception.
 *
 * @param {String} message Message that informs about error.
 * @param {String} category Category of exception (internal or external).
 * @param {String} location Code reference to exception source.
 */
CommonUtils.throwException = function(message, category, location) {
	if (CommonUtils.isNullOrUndefined(message) || message === "") {
		message = "Unknown message";
	}

	if (CommonUtils.isNullOrUndefined(category) || category === "") {
		category = "Unknown category";
	}

	if (CommonUtils.isNullOrUndefined(location) || location === "") {
		location = CommonUtils.throwException.caller === null ? "Unknown location" : CommonUtils.throwException.caller;
	}

	var error = category + " error " + "[" + location + "]" + ":" + message;

	alert(error);

	throw(error);
};

/**
 * Evaluates string to an object if possible.
 *
 * @param {String | Object} string String representation of an object to be evaluated or an object itself.
 *
 * @return Evaluated object
 * @type {Object}
 */
CommonUtils.evaluate = function(string) {
	return typeof string === "string" ? eval("(" + string + ")") : string; 
};

/**
 * Checks if given value is a value of given object.
 *
 * @param value Value to check if is a value of given object.
 * @param object Object to be inspected to contain checked value.
 *
 * @return <code>true<code> if value belongs to the object, <code>false</code> in another case.
 */
CommonUtils.isValueInObject = function(value, object) {
	for (var key in object) {
		if (value == object[key]) {
			return true;
		}
	}

	return false;
};
