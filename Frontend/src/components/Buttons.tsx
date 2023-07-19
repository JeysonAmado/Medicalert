import '../assets/css/Buttons.css';
import {IonButton, IonRouterLink} from '@ionic/react';
interface ButtonsProps{
    label : string
    route ? : string
    event? : () => void | Promise<void>
}
export const Buttons: React.FC<ButtonsProps> = ({label,route,event}) => {

    if (route) {
        return (
            <IonRouterLink href={route}>
                <IonButton onClick={event} className="GradientButton">{label}</IonButton>
            </IonRouterLink>
        );
    } else {
        return (
            <IonButton onClick={event} className="GradientButton">{label}</IonButton>
        );
    }
}