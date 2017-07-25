<div class="container" ng-controller="<?= $pageController ?>">
	<section id="content">
		<div class="identity-div">
			<div class="identity-text" ng-click="go('/profile')">ACCOUNT: {{ userName }}</div>
			<div class="back-text" ng-click="<?= $pageBack ?>">BACK</div>
		</div>
		<div class="header"><?= $pageHeader ?></div>
		<div class="sub-header"><?= $pageSubtitle ?></div>
		
		<div id="option-group" class="group">		
			<div class="message">{{ message }}</div>
			<?php  include_once( $pageBody )?>
		</div>
		
		
<?php 
// action buttons
if ( isset( $actionButtons ) )
{
	echo( '<div class="action-btn-div">' );
	foreach ( $actionButtons as $i => $button )
	{
		echo ( '<input type="' . $button['type'] . '" class="action-btn" value="' . $button['value'] . '" ng-click="' . $button['ngClick'] . '"/>');
	}
	echo( '</div>' );
}
?>
		
		
<?php 
if ( isset( $actionLinks) )
{
	// action links
	echo( '<div class="group-link-div">' );
	foreach ( $actionLinks as $i => $link )
	{
		echo ( '<a ng-click="' . $link['ngClick'] . '" class="form-anchor">' . $link['title'] . '</a><br>');
	}
	echo( '</div>' );
}
?>

	</section>
</div>