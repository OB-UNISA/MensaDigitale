<%@page import="business.richieste.RichiestaBean"%>
<%@page import="business.richieste.RichiesteInSospeso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html style="height: auto;width: auto;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Richieste in Sospeso</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/Footer-Basic.css">
    <link rel="stylesheet" href="assets/css/Google-Style-Login.css">
    <link rel="stylesheet" href="assets/css/Header-Dark.css">
    <link rel="stylesheet" href="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body style="background-image: url(&quot;assets/img/food.jpg&quot;);height: auto;width: auto;max-height: none;">
    <nav class="navbar navbar-light fixed-top text-dark" style="background-color: #FF9900;">
        <div class="container-fluid"><img class="img-fluid" src="assets/img/Logo.png" style="width: 255px;padding: 0px;margin: -29px;"><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div
                class="collapse navbar-collapse text-right float-right" id="navcol-1">
                <ul class="nav navbar-nav mx-auto">
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="#">Login</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="#">Consulta Men�</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="#">Inserisci Men�</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="#">Valuta Servizio</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="#">Prenotazione</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="#">Servizi Ristorazione</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="nav-item" role="presentation"></li>
                </ul>
        </div>
        </div>
    </nav>
    <div style="margin-top: 150px;">
        <div class="container">
            <div class="row">
                <div class="col-md-12" style="background-color: rgba(255,255,255,0.92);">
                    <h1 style="font-family: Montserrat, sans-serif;margin-top: 15px;">Richieste di Attivazione dei Servizi in Sospeso</h1>
                    <p style="font-family: Montserrat, sans-serif;">Seleziona la Richiesta da Visionare</p>
                    <%
							RichiesteInSospeso ris = RichiesteInSospeso.getInstance();
							if (ris.getListaRichieste().isEmpty()) {
							%>
							<div class="border rounded-0 border-dark"><button class="btn btn-primary" type="submit" style="border: none; box-shadow:none; margin-top: 5px;margin-bottom: 5px;margin-left: 15px;width: auto;height: auto;background-color: rgba(0,123,255,0);color: rgb(33,37,41);font-family: Montserrat, sans-serif;font-size: 25px;padding: 0px;">&#x1F4E7; Nessuna Richiesta in Sospeso</button></div>
							<%
							} else {
							%>
								<%
								for (RichiestaBean r : ris.getListaRichieste()) {
								%>
										<form method="post" action="<%=response.encodeURL("/ValutaRichiesta")%>">
											<div class="border rounded-0 border-dark"><button id="id" value="<%=r.getId() %>" class="btn btn-primary" type="submit" style="border: none; box-shadow:none; margin-top: 5px;margin-bottom: 5px;margin-left: 15px;width: auto;height: auto;background-color: rgba(0,123,255,0);color: rgb(33,37,41);font-family: Montserrat, sans-serif;font-size: 25px;padding: 0px;">&#x1F4E7; <%=r.getEmail() %></button></div>
										</form>
								<%
								}
								%>
							<%
							}
							%>
                </div>
            </div>
        </div>
    </div>
    <div class="text-center footer-basic" style="margin: 0px;background-color: rgb(20,20,20);margin-top: 100px;">
        <figure class="figure"><img class="img-fluid figure-img" src="assets/img/adisurclogo.png">
            <figcaption class="figure-caption">Agenzia per il Diritto allo Studio della Regione Campania � 2021<br></figcaption>
        </figure>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.js"></script>
</body>

</html>