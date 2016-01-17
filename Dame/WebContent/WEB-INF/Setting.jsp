<div id="SettingsContainer">
	<p id="SettingsHeader">Einstellungen</p>
	<form>
		<p>Spieler 1:<input id="PLAYER1" type="text" name="PLAYER1" required />KI: <input onclick="CB_KI('CB_K1', 'PLAYER1');" id="CB_K1" style="margin:0px; padding:0px;" type="checkbox" value=true name="P1_KI" /></p>
		<p>Spieler 2:<input id="PLAYER2" type="text" name="PLAYER2" required />KI: <input onclick="CB_KI('CB_K2', 'PLAYER2');" id="CB_K2" style="margin:0px; padding:0px;" type="checkbox" value=true name="P2_KI" /></p>
		<p><button type="submit">Senden</button></p>
	</form>
	<script>
		function CB_KI(NameCB, NameINPUT)
		{
			if (document.getElementById(NameCB).checked){
			  	document.getElementById(NameINPUT).disabled = true;
				document.getElementById(NameINPUT).value = "Computer";
		 	}
		  	else{
				document.getElementById(NameINPUT).disabled = false;
			  	document.getElementById(NameINPUT).value = "";
		  	}
		}
	</script>
	<form>
	<p>Dateipfad:<input id="Path" type="text" name="Path" required/><input type="radio" name="Laden" value="CSV" />CSV<br></p>
	<p>Name der Datei:<input id="Dateiname" type="text" name="Dateiname" required></p>
	<p><input type="radio" name="Laden" value="Serialisiert" />Serialisiert<br><input type="radio" name="Laden" value="XML" />XML<br></p>
	<p><button type="submit">Laden...</button></p>	
	</form>
</div>