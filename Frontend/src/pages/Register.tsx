import {IonContent, IonHeader, IonPage, IonTitle} from "@ionic/react";
import {Buttons} from "../components/Buttons";

const Register: React.FC = () => {

    return (
        <IonPage>
            <IonContent>
                <IonTitle>Registro</IonTitle>
                <ion-item>
                    <ion-input type="nombre" placeholder="Nombre"></ion-input>
                </ion-item>
                <ion-item>
                    <ion-input type="email" placeholder="Ingrese email"></ion-input>
                </ion-item>
                <ion-item>
                    <ion-input  type="password" placeholder="Contraseña"></ion-input>
                </ion-item>
                <ion-item>
                    <ion-input  type="password" placeholder="Confirme contraseña"></ion-input>
                </ion-item>
                <Buttons label={"Iniciar sesión"} route={"/"}></Buttons>
            </IonContent>
        </IonPage>
    );
};

export default Register;
