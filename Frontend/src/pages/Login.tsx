import {IonContent, IonInput, IonItem, IonPage, IonTitle} from "@ionic/react";
import {Buttons} from "../components/Buttons";
import axios from 'axios';
import {useState} from "react";

const Login: React.FC = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [counter, setCounter] = useState(0);

    console.log(useState());
    console.log(password);

    const sumar = async () => setCounter(counter+1);
    const restar = async () => setCounter(counter-1);

    return (
        <IonPage>
            <IonContent>
                <IonTitle>{counter}</IonTitle>
                <IonTitle>{counter}</IonTitle>
                <IonTitle>{counter}</IonTitle>
                    <IonItem>
                        <IonInput
                            type="email"
                            placeholder="Ingrese email"
                            onIonChange={e => setEmail(e.detail.value!)}
                        ></IonInput>
                    </IonItem>
                    <IonItem>
                        <IonInput
                            type="password"
                            placeholder="Contraseña"
                            onIonChange={e => setPassword(e.detail.value!)}
                        ></IonInput>
                    </IonItem>
                <Buttons label={"Sumar"} event={sumar}></Buttons>
                <Buttons label={"Restar"} event={restar}></Buttons>
            </IonContent>
        </IonPage>
    );
};

export default Login;

