/**
 * @fileoverview Handles user application logic.
 */


/**
 * @class Holds application logic.
 * @constructor
 */
var EventHandler = function() {
};

EventHandler.editProfile = function(formId) {
	var elements = document.forms[formId].getElementsByTagName("div");;
	
	for (var i = 0; i < elements.length; i++){
		if ((elements[i].id).indexOf("edit_") != -1) {
			EventHandler.appendStyle(elements[i], "show");
		} else if ((elements[i].id).indexOf("view_") != -1){
			EventHandler.appendStyle(elements[i], "hidden");
		}
	}
};

/**
 * Appends a CSS class to existing input HTML element style. Does nothing if style already exists.
 * 
 * @param {DomElement} element div or span
 * @param {String} styleClass CSS class
 * 
 * @see HtmlUtils.getAttribute
 */
EventHandler.appendStyle = function(element, styleClass) {
	var oldStyle = EventHandler.getAttribute(element, CommonUtils.CLASS_ATTRIBUTE);

	if (oldStyle === null) {
		element.setAttribute(CommonUtils.CLASS_ATTRIBUTE, styleClass);
	} else if(oldStyle.search(styleClass) == -1) {
		element.setAttribute(CommonUtils.CLASS_ATTRIBUTE, oldStyle + " " + styleClass);
	}
};

/**
 * Appends a CSS class to existing input HTML element style. Does nothing if style already exists.
 * 
 * @param {DomElement} element div or span
 * @param {String} styleClass CSS class
 * 
 * @see HtmlUtils.getAttribute
 */
EventHandler.setStyle = function(element, styleClass) {
	element.setAttribute(CommonUtils.CLASS_ATTRIBUTE, styleClass);
};




/**
 * Gets HTML element attribute by name.
 * 
 * @param {DomElement} element any HTML element
 * @param {String} name attribute name
 *
 * @return Attribute value or null if there is no such attribute defined for element.
 * @type String
 */
EventHandler.getAttribute = function(element, name) {
	try {
		return element.getAttribute(name);
	} catch (ex) {
		return null;
	}
};