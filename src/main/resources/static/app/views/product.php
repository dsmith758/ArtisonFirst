<?php

$basedir = $_SERVER ['DOCUMENT_ROOT'];
include_once ($basedir . '/common/init.php');

// page definitions
$pageBack = "go('/main')";
$pageController = 'productController';
$pageHeader = 'Products';
$pageSubtitle = 'These are your products';
$pageBody = $basedir . '/app/views/body/product-body.php';

// Buttons
$actionButtons = array (
		0 => array (
				"type" => 'button',
				"value" => "ADD PRODUCT",
				"ngClick" => "addProduct()"
		),
		1 => array (
				"type" => 'button',
				"value" => "CANCEL",
				"ngClick" => "go('/main')"
		)
);

// Links
$actionLinks = array (
		0 => array (
				"ngClick" => "go('/reset-password')",
				"title" => "Reset Password"
		),
		1 => array (
				"ngClick" => "go('/business')",
				"title" => "Manage Business Profile"
		),
		2 => array (
				"ngClick" => "go('/addProfile')",
				"title" => "Add New User"
		)
);


include_once ($basedir . "/app/views/templates/container-template.php");

?>