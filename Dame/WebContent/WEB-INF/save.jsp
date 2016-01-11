<%@ page import="GameLogic.*,SavegameManager.*,Interfaces.*,Enumerations.*,java.util.*,java.awt.*"%>

<div id = "Save Game">
 <form action="">
 
 <p>File name:
  <input type="text" name="filename" required></p>
  
<p><input type="radio" name="Filetype" value="CSV" />CSV<br>
<input type="radio" name="Filetype" value="PDF" />PDF<br>
<input type="radio" name="Filetype" value="Serialised" />Serialised<br>
<input type="radio" name="Filetype" value="XML" />XML</p>

<button type="submit">Save</button>
</form>
</div> 