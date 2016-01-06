<%@ page import="GameLogic.*,Enumerations.*,java.util.*,java.awt.*"%>

<div  id="winViewContainer">
	<%
		// Get game object
		SpielBean game = (SpielBean) request.getAttribute("GAME");
	
		FarbEnum winner = game.gameFinished();
		if(winner != null){
			Spieler gamer[] = game.getGamer();
			String gamerName;
			if(gamer[0].getColor() == winner){
				if(gamer[0].getKi() != null) gamerName = "Computer 1";
				else gamerName = gamer[0].getName();
			}
			else{
				if(gamer[1].getKi() != null) gamerName = "Computer 2";
				else gamerName = gamer[1].getName();
			}
			
			if(winner == FarbEnum.schwarz) out.println("<div class=\"centerDiv Text3DLight\" id=\"winViewBlack\"><p>" + gamerName + "</p><p>WINNER</p></div>");
			else out.println("<div class=\"centerDiv Text3DLight\" id=\"winViewWhite\"><p>" + gamerName + "</p><p>WINNER</p></div>");
		}
	%>
	<form action="">
		<input type="hidden" value="true" name="NewGame" />
		<button type="submit"> Neustart</button>
	</form>
</div>