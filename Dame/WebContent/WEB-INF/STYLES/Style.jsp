<style>
	.centerDiv{
		width: 50%;
	    margin: 0 auto;
	}
	
	.floatLeft{
		float:left;
	}
	
	.floatRight{
		float:right;
	}
	
	.floatCenter{
		display: inline-block;
	    margin:0 auto;
	}
	
	.grayScale{
		-webkit-filter: grayscale(100%);
		filter: grayscale(100%);
	}
	
	.hoverShadow{
		-webkit-transition: all 0.5s ease;
	     -moz-transition: all 0.5s ease;
	       -o-transition: all 0.5s ease;
	      -ms-transition: all 0.5s ease;
	          transition: all 0.5s ease;
	}
	
	.hoverShadow:hover{
		-webkit-filter: drop-shadow(0px 0px 10px black)
	}
	
	.hoverGrow{
		-webkit-transition: all 0.5s ease;
	     -moz-transition: all 0.5s ease;
	       -o-transition: all 0.5s ease;
	      -ms-transition: all 0.5s ease;
	          transition: all 0.5s ease;
	}
	
	.hoverGrow:hover{
		-webkit-transform: scale(1.3);
	        -ms-transform: scale(1.3);
	            transform: scale(1.3);
	}
	
	.hoverTilt{
		-webkit-transition: all 0.5s ease;
	     -moz-transition: all 0.5s ease;
	       -o-transition: all 0.5s ease;
	      -ms-transition: all 0.5s ease;
	          transition: all 0.5s ease;
	}
	
	.hoverTilt:hover{
		-webkit-transform: rotate(30deg);
	     -moz-transform: rotate(30deg);
	       -o-transform: rotate(30deg);
	      -ms-transform: rotate(30deg);
	          transform: rotate(30deg);
	}
	
	.hoverRevertTilt{
		-webkit-transition: all 0.5s ease;
	     -moz-transition: all 0.5s ease;
	       -o-transition: all 0.5s ease;
	      -ms-transition: all 0.5s ease;
	          transition: all 0.5s ease;
	}
	
	.hoverRevertTilt:hover{
		-webkit-transform: rotate(-30deg);
	     -moz-transform: rotate(-30deg);
	       -o-transform: rotate(-30deg);
	      -ms-transform: rotate(-30deg);
	          transform: rotate(-30deg);
	}
	
	.hoverRotate{
		-webkit-transition: all 0.5s ease;
	     -moz-transition: all 0.5s ease;
	       -o-transition: all 0.5s ease;
	      -ms-transition: all 0.5s ease;
	          transition: all 0.5s ease;
	}
	
	.hoverRotate:hover{
		-webkit-transform: rotate(360deg);
	     -moz-transform: rotate(360deg);
	       -o-transform: rotate(360deg);
	      -ms-transform: rotate(360deg);
	          transform: rotate(360deg);
	}
	
	.pressGrayScale{
		-webkit-transition: all 0.5s ease;
	     -moz-transition: all 0.5s ease;
	       -o-transition: all 0.5s ease;
	      -ms-transition: all 0.5s ease;
	          transition: all 0.5s ease;
	}
	
	.pressGrayScale:active{
		-webkit-filter: grayscale(50%);
		filter: grayscale(50%);
	}
	
	.Text3D{
	    text-shadow: 6px 6px 6px #000000;
	}
	
	.Text3DLight{
	    text-shadow: 2px 2px 2px #000000;
	}
	
	.itemHidden {
	  -webkit-transition: opacity 0.5s ease-in-out;
	  -moz-transition: opacity 0.5s ease-in-out;
	  -ms-transition: opacity 0.5s ease-in-out;
	  -o-transition: opacity 0.5s ease-in-out;
	  transition: opacity 0.5s ease-in-out;
	
	  filter: alpha(opacity=0);
	  opacity: 0;
	}
	
	.itemShown {
	  -webkit-transition: opacity 0.5s ease-in-out;
	  -moz-transition: opacity 0.5s ease-in-out;
	  -ms-transition: opacity 0.5s ease-in-out;
	  -o-transition: opacity 0.5s ease-in-out;
	  transition: opacity 0.5s ease-in-out;
	
	  filter: alpha(opacity=100);
	  opacity: 1;
	}
	
	/*##################################################*/
	/*## Generel layout */
	
	body{
		background-image:url("./IMAGES/background.png");
		background-size: 100% 100%;
		background-color:black;	
	}
	
	p{
		color:white;	
	}
	
	#mainDiv{
		background-color:rgba(0,0,0,0.5);
		
		margin-top:50px;
		
		height: 600px;
		width: 800px;
	}
	
	/*##################################################*/
	/*## Settings layout */
	
	#SettingsContainer{
		position:relative;
		top: 100px;
		
		text-align:center;
	}
	
	#SettingsContainer, #SettingsContainer input{
		font-size:30px;
	}
	
	#SettingsContainer input{
		margin-left:50px;
	}
	
	#SettingsHeader{
		font-size:50px;
		padding-bottom:50px;
	}
</style>