/*
Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:     
	config.language = 'zh-cn'; //��������     
	config.uiColor = '#BFEFFF'; //������ɫ     
	//config.width = 700; //����     
	//config.height = 300; //�߶�     
	config.skin='kama';    
	//������     
	config.toolbar =     
	[     
	    ['Source','Bold','Italic'],     
	    ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],    
	    ['Smiley'],      
	    ['Styles','Font','FontSize'],     
	    ['TextColor']   
	    
	]; 
};
