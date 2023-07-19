import {
    IonApp, IonContent, IonHeader, IonPage,
    IonTitle
} from '@ionic/react';
import { IonReactRouter } from '@ionic/react-router';
import '@ionic/react/css/core.css';

/* Theme variables */
// import '../theme/variables.css';
import {Buttons} from "../components/Buttons";
import '../assets/css/HomeStyle.css'
import React from "react";
import {LOGIN, REGISTER} from "../constans/routePaths";
import {CenterElements} from "../components/CenterElements";


const Home: React.FC = () => (
    <IonPage>
        <IonReactRouter>

            <IonContent>
                <CenterElements>
                    <h3>Medicalert</h3>
                </CenterElements>
                <CenterElements>
                    <Buttons  label = {"Iniciar sesión"} route={LOGIN}/>
                    <Buttons  label = {"Registrarse"} route={REGISTER}/>
                </CenterElements>
            </IonContent>
        </IonReactRouter>
    </IonPage>
);

export default Home;
