var sessionId = "";
var forward = "";

function isAuthenticated(){
	$.get( 'IsAuthenticated.php', function( data, status ) {
		sessionId = data;
		alert( sessionId );
		if ( sessionId == 'false' ){
			sessionId = "";
			return false;
		}
	});
	return true;
}

function authenticate( userName, password ){
	alert( authenticating );
	/*
	data = { 'user_name' : userName, 'password' : password };
	$.post( 'security.php', data, function(data){
		sessionId = data;
		alert( sessionId );
		if ( sessionId == 'false' ){
			sessionId = "";
			return false;
		}		
	});
	*/
	return true;
}

function getPage(page) {
	$.get(page, function(data) {
		$('#pageLoader').html(data);
	}).done(function() {
		loadPages();
	});
}

function loadPage(page) {
	$.get(page, function(data) {
		$('#MainBody').html(data);
	}).done(function() {
		alert(data);
	});
}

function loadMenu(page) {
	$.get(page, function(data) {
		$('#MenuBody').html(data);
	});
}

function init() {
	loadPages();
}

function postForm(form, page) {
	var data = form.serialize();
	$.post( page, data, function() {
		$('#pageLoader').html(data);
		loadPages();		
	} );
}

function toggleMenuBtn() {

	if (!$('#menu-toggle').is(':checked')) {
		openMenu();
	} else {
		closeMenu();
	}
}

function openMenu() {
	$('#menu-toggle').prop('checked', true)
	$('nav').css({
		'left' : '0'
	});
}

function closeMenu() {
	$('#menu-toggle').prop('checked', false)
	$('nav').css({
		'left' : '-16em'
	});
}

function loadLandingPage() {
	loadPage('page/landing.php');
	loadMenu('menu/main-menu.php');
	$("#msg-icon").show();
	closeMenu();
}

function loadRegisterPage() {
	loadPage('page/register.php');
	loadMenu('menu/main-menu.php');
	$("#msg-icon").show();
	closeMenu();
}

function loadProfilePage() {
	loadPage('page/profile.php');
	loadMenu('menu/main-menu.php');
	$("#msg-icon").show();
	closeMenu();
}

function loadProductPage() {
	loadPage('page/product.php');
	loadMenu('menu/main-menu.php');
	$("#msg-icon").show();
	closeMenu();
}

function loadNewProductPage() {
	loadPage('page/new-product.php');
	loadMenu('menu/main-menu.php');
	$("#msg-icon").show();
	closeMenu();
}

function loadEditProductPage() {
	loadPage('page/edit-product.php');
	loadMenu('menu/main-menu.php');
	$("#msg-icon").show();
	closeMenu();
}

function loadInboxPage() {
	loadPage('page/inbox.php');
	loadMenu('menu/main-menu.php');
	$("#msg-icon").show();
	closeMenu();
}

function loadMessagePage() {
	loadPage('page/message.php');
	loadMenu('menu/main-menu.php');
	$("#msg-icon").show();
	closeMenu();
}

function loadOutboxPage() {
	loadPage('page/outbox.php');
	loadMenu('menu/main-menu.php');
	$("#msg-icon").show();
	closeMenu();
}

function loadContactPage() {
	loadPage('page/contact.php');
	loadMenu('menu/main-menu.php');
	$("#msg-icon").show();
	closeMenu();
}

function loadEditContactPage() {
	loadPage('page/edit-contact.php');
	loadMenu('menu/main-menu.php');
	$("#msg-icon").show();
	closeMenu();
}

function loadNewContactPage() {
	loadPage('page/new-contact.php');
	loadMenu('menu/main-menu.php');
	$("#msg-icon").show();
	closeMenu();
}

function logOut() {
	getPage('logout.php');
	closeMenu();
}
