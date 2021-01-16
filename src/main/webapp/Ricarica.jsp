<%@ page import="business.consumatore.ConsumatoreBean"%>
<%@ page language="java" contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15"%>
<% 
    ConsumatoreBean consumatore = (ConsumatoreBean) request.getSession().getAttribute("utente"); //TODO replace with Utente when ready
    if (consumatore == null){ //DA CAMBIARE
      
      response.sendRedirect(request.getContextPath() + "/index.jsp");
      
      return;
    }
%>
<!DOCTYPE html>
<html style="height: auto;width: auto;">

<head>
	<script
		src="https://code.jquery.com/jquery-3.5.1.min.js"
		integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		crossorigin="anonymous"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Ensures optimal rendering on mobile devices. -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<!-- Optimal Internet Explorer compatibility -->
    <meta charset="iso-8859-15">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Ricarica del Saldo</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
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
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="#">Consulta Menù</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link active" href="#">Inserisci Menù</a></li>
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
            <div class="row justify-content-center">
                <div class="col-md-6" style="background-color: rgba(255,255,255,0.92);padding-bottom: 15px;padding-top: 15px;max-width: 95%;width: 357px;">
                    <div id="importo">
                        <h1 style="font-family: Montserrat, sans-serif;">Scegli Importo da Ricaricare</h1>
                        <div class="form-check"><input class="form-check-input" type="radio" id="importo" name="importo" value="5.00"><label class="form-check-label" for="formCheck-1">5 &euro;</label></div>
                        <div class="form-check"><input class="form-check-input" type="radio" id="importo" name="importo" value="10.00"><label class="form-check-label" for="formCheck-1">10 &euro;</label></div>
                        <div class="form-check"><input class="form-check-input" type="radio" id="importo" name="importo" value="20.00"><label class="form-check-label" for="formCheck-1">20 &euro;</label></div>
                        <div class="form-check"><input class="form-check-input" type="radio" id="importo" name="importo" value="50.00"><label class="form-check-label" for="formCheck-1">50 &euro;</label></div>
                    </div>
                    <div>
				                    <div id="paypal-button-container"></div>
						<script src="https://www.paypal.com/sdk/js?client-id=ASmzurgdl5tJiqNKlv73MgpFOg3XRbu4Dau8g_wDUXbrZwaEBW4LfZgUIkmAYz34NGgTF2zSsSxlbmMs&currency=EUR&disable-funding=sofort,mybank"></script>
					
					
						<script>
						var importo = null;
					    paypal.Buttons({   
					        createOrder: function(data, actions) {
					        	importo =document.querySelector("#importo input[type=radio]:checked").value;
					        	
					          return actions.order.create({
					            purchase_units: [{
					              amount: {
					                value:importo
					              }
					            }]
					          });
					        },
					        onApprove: function(data, actions) {
					          return actions.order.capture().then(function(details) {
					            console.log(details);
					            $.ajax({
					            	type:"POST", url:"./RicaricaSaldo", data:{transaction_id:details.id,amount:importo}, 
					            	success: function (data) {
					            		alert("Transazione Eseguita");
					            	}
					            })
					        	  
					          });
					        }
					      }).render('#paypal-button-container'); // Display payment options on your web page
					    </script>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="text-center footer-basic" style="margin: 0px;background-color: rgb(20,20,20);">
        <figure class="figure"><img class="img-fluid figure-img" src="assets/img/adisurclogo.png">
            <figcaption class="figure-caption">Agenzia per il Diritto allo Studio della Regione Campania © 2021<br></figcaption>
        </figure>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.js"></script>
</body>

</html>