<%@ page import="GameLogic.*,Enumerations.*,java.util.*,java.awt.*"%>

<div id="GameCurrentPlayer">
	<%
		// Get game object
		SpielBean game = (SpielBean) request.getAttribute("GAME");

		// Get color
		String colorPlayer1 = "rgb(50,50,50)";
		String colorPlayer2 = "rgb(50,50,50)";
		if (game.getCurrentGamer().getColor() == FarbEnum.schwarz)
			colorPlayer1 = "rgb(0,100,120)";
		else
			colorPlayer2 = "rgb(0,100,120)";

		// Get names
		String namePlayer1 = game.getPlayer(1).getName();
		if (game.getPlayer(1).getKi() != null)
			namePlayer1 = "Computer 1";
		String namePlayer2 = game.getPlayer(2).getName();
		if (game.getPlayer(2).getKi() != null)
			namePlayer1 = "Computer 2";

		// Create player
		out.println("<div id=\"GamePlayer1\" class=\"floatLeft\" style=\"background-color:" + colorPlayer1
				+ "\"><p>" + namePlayer1 + "</p></div>");
		out.println("<div id=\"GamePlayer2\" class=\"floatRight\" style=\"background-color:" + colorPlayer2
				+ "\"><p>" + namePlayer2 + "</p></div>");
	%>
	<div style="clear: both;"></div>
</div>
<div id="GameGameboard" class="centerDiv">
	<%
		// Get gameboard
		Spielbrett gameboard = game.getGameboard();

		// Get field
		Spielfeld felder[][] = gameboard.getFields();

		// Print table-tag
		out.println("<table>");

		// Generate fields and labels
		if (game != null && felder != null) {
			// Draw columns
			for (int i = 0; i < felder.length; i++) {
				// Start row
				out.println("<tr>");
				for (int p = -1; p < felder[i].length; p++) {
					// Start column
					out.println("<td>");

					// Check if number or field
					if (p == -1) {
						out.println("<p>" + (felder.length - i) + "</p>");
					} else {
						// Get field color
						String fieldColor = "";
						if (game.getFieldClicked() != null) {
							if (game.getFieldClicked().equals(new Point(p, game.getGameboardSize() - i - 1)))
								fieldColor = "rgb(255,215,0)";
						}

						if (fieldColor.isEmpty()) {
							if ((i + p) % 2 != 0)
								fieldColor = "rgb(50,50,50)";
							else
								fieldColor = "rgb(170,170,170)";
						}

						// Get figureColor
						Spielfeld field = game.getGameboard().getField(p,
								game.getGameboard().getFields().length - i - 1);
						out.println("<div id=\"GameField\" style=\"background-color:" + fieldColor + "\">");
						if (field != null && field.getFigure() != null) {
							String figure = "<div id=\"GameFigure\">";

							if (field.getFigure().getColor() == FarbEnum.schwarz)
								figure += "<div class=\"centerDiv\" style=\"border-color:rgb(40,40,40); background-color:rgb(0,0,0)\">";
							else
								figure += "<div class=\"centerDiv\" style=\"border-color:rgb(150,150,150); background-color:rgb(255,255,255)\">";

							figure += "<a href=\"?FieldPressed=" + String.valueOf((char) (p + 65))
									+ String.valueOf(game.getGameboardSize() - i) + "\"></a>";

							figure += "</div></div>";

							out.println(figure);
						}

						out.println("<a href=\"?FieldPressed=" + String.valueOf((char) (p + 65))
								+ String.valueOf(game.getGameboardSize() - i) + "\"></a>");

						out.println("</div>");
					}

					// End column
					out.println("</td>");
				}
				// End row
				out.println("</tr>");
			}

			// Start row
			out.println("<tr>");
			for (int i = 0; i <= felder.length; i++) {
				// Start column
				out.println("<td>");

				// Print letter
				if (i > 0) {
					out.println("<p>" + String.valueOf((char) ((i - 1) + 65)) + "</p>");
				}

				// End column
				out.println("</td>");
			}
			// End row
			out.println("</tr>");
		}

		// Print table-end-tag
		out.println("</table>");
	%>
</div>
<div id="GameFoot" class="centerDiv">
	<div id="GameLogging" class="floatLeft">
		<%
			String loggingText = "";
			ArrayList<String> logs = Logging.globalPointer.getLogs();
			for(int i = logs.size() - 1; i >= 0; i--){
				if (!loggingText.isEmpty())
					loggingText += "&#13;&#10;";
				loggingText += logs.get(i);
			}

			out.println("<textarea readonly>" + loggingText + "</textarea>");
		%>
	</div>
	<div class="floatRight">
		<form action=""><button type="submit">Refresh</button></form>
	</div>
	<div style="clear:both"></div>
	<script>
		function refresh() {
		    setTimeout(function(){ window.location = window.location.href; }, 1000);
		}
		<%
			if(game.getCurrentGamer().getKi() != null){
				out.println("refresh();");
			}
		%>
	</script>
</div>