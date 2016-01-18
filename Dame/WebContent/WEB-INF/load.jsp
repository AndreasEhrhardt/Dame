<div id = "Load Game">
 <form action="">
	<p>Dateipfad:<input id="Path" type="text" name="Path" required/></p>
	<p>Name der Datei:<input id="Dateiname" type="text" name="Dateiname" required></p>
	
	<p><input type="radio" name="Laden" value="CSV" />CSV<br>
	<input type="radio" name="Laden" value="Serialisiert" />Serialisiert<br>
	<input type="radio" name="Laden" value="XML" />XML<br></p>
	<p><input type="hidden" name="LoadGame" value="true" />
	<button type="submit">Load Game</button></p>


</form>
</div> 