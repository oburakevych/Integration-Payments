<%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles" %>

<h2>
Signup
</h2>

<p>To create a Poll account, please fill out the fields below and press Submit when you are finished.
	Fields marked with an asterisk <span class="ast">*</span> are required.
</p> 

<div id="signup_frame">
	<tiles:insertAttribute name="new_account"/>
</div>	