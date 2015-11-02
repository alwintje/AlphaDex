<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Aplhadex</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="container">
		<div class="grid">
			<div class="grid-item one-whole">
				<div class="absolute-center">
					<div class="login-window">
						<div class="login-form">
							<form id='login' action='login.php' method='post' accept-charset='UTF-8'>
								<fieldset class="loginform-fieldset">
									<input type='hidden' name='submitted' id='submitted' value='1'/>
									<input type='text' name='username' id='username'  maxlength="50" placeholder="E-mail/Gebruikersnaam"/>
									<input type='password' name='password' id='password' maxlength="50" placeholder="Wachtwoord"/>
									<br>
									<input id="submit-button" type='submit' name='Submit' value='Inloggen' />
									 
								</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
