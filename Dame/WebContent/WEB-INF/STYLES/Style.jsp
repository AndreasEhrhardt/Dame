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
		background-color:black;
		
		margin:0px;
		padding:0px;	
	}
	
	p{
		color:white;	
	}
	
	#mainDiv{
		background-color:rgba(0,0,0,0.5);
		
		margin-top:20px;
		margin-bottom:70px;
		
		height: 650px;
		width: 800px;
		
		border:2px solid black;
		border-radius:10px;
		
		overflow:hidden;
	}
	
	#footer{
		position:fixed;
		
		height:50px;
		width:100%;
		
		border:0px solid gray;
		border-top-width:2px;
		
		margin:0px;
		padding:0px;
		margin-top:-52px;
		
		bottom:0;
		
		text-align:center;
		background-color:black;
		
		clear:left;
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
		margin-left:30px;
		margin-right:30px;
	}
	
	#SettingsHeader{
		font-size:40px;
		padding-bottom:10px;
	}
	
	/*##################################################*/
	/*## Game layout */
	
	#GameGameboard{
		margin-top:20px;
	}
	
	#GameGameboard td{
		width:30px;
		height:30px;
		
		padding:0px;
		
		text-align:center;
	}
	
	#GameCurrentPlayer{
		border:0px solid gray;
		border-bottom-width:2px;
	}
	
	#GameCurrentPlayer p{
		margin:0px;
		margin-top:5px;
		text-align:center;
		font-size:25px;
	}
	
	#GameCurrentPlayer > div:nth-of-type(1), #GameCurrentPlayer > div:nth-of-type(2){
		margin-left:5%;
		margin-right:5%;
		margin-top:20px;
		margin-bottom:20px;
		
		background-color:black;
		
		border:2px solid gray;
		border-radius:10px;
		
		height:40px;
		width:30%;
	}
	
	#GameFoot{
		width:100%;
		height:100%;
		
		border:0px solid gray;
		border-top-width:2px;
		
		padding-top:10px;
		padding-bottom:10px;
		
		text-align:center;
	}
	
	#GameLogging textarea{
		width:600px;
		height:80px;
		
		resize: none;
		margin:0px;
		margin-top:10px;
		margin-left:10px;
		
		border:2px solid;
		border-radius:10px;
		
		background-color:black;
		color:white;
	}
	
	#GameField{
		height:100%;
		width:100%;
	}
	
	#GameField a {
		display:block;
		height:100%;
	}
	
	#GameFigure{
		position:relative;

		width:100%;
		height:0px;
	}
	
	#GameFigure > div{	
		height:25px;
		width:25px;
		
		border:2px solid;
		border-radius:50%;
	}
	
	/*##################################################*/
	/*## Winnning layout */
	
	#winViewContainer{
		width:100%;
		height:100%;
	}
	
	#winViewContainer > div{
		width:500px;
		height:500px;
		
		margin-top:50px;
		
		border:5px solid gray;
		border-radius:50%;
		
		text-align:center;
		
		-moz-box-sizing:    border-box;
	   -webkit-box-sizing: border-box;
	    box-sizing:        border-box;
	    
	    font-size:45px;
	}
	
	#winViewBlack p, #winViewWhite p{
		padding-top:60px;
	}
	
	#winViewBlack p{
		color:black;
	}
	
	#winViewBlack{
		background-color:black;
	}
	
	#winViewWhite p{
		color:black;
	}
	
	#winViewWhite{
		background-color:white;
	}
</style>