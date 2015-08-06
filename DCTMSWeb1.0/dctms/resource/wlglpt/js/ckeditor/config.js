/*
Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:     
	config.language = 'zh-cn'; //配置语言     
	config.uiColor = '#BFEFFF'; //背景颜色     
	//config.width = 700; //宽度     
	//config.height = 300; //高度     
	config.skin='kama';    
	//工具栏     
	config.toolbar =     
	[     
	    ['Source','Bold','Italic'],     
	    ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],    
	    ['Smiley'],      
	    ['Styles','Font','FontSize'],     
	    ['TextColor']   
	    
	]; 
};
