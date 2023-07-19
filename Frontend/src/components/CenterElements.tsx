import '../assets/css/CenterElements.css'

interface CenterElementsProps{
    children : string
}


export const CenterElements: React.FC<CenterElementsProps> = ({children}) => {

    return (
        <div className="centered-content">
            {children}
        </div>
    );
}