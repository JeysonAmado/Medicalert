import { Route } from 'react-router-dom';
import {
  IonApp,
  IonRouterOutlet,
  setupIonicReact
} from '@ionic/react';
import { IonReactRouter } from '@ionic/react-router';
import '@ionic/react/css/core.css';

/* Basic CSS for apps built with Ionic */
import '@ionic/react/css/normalize.css';
import '@ionic/react/css/structure.css';
import '@ionic/react/css/typography.css';

/* Theme variables */
import './theme/variables.css';
import {HOME, LOGIN, MAIN_PAGE, REGISTER} from "./constans/routePaths";
import MainPage from "./pages/MainPage";
import Login from "./pages/Login";
import Home from "./pages/Home";
import Register from "./pages/Register";

setupIonicReact();

const App: React.FC = () => (
    <IonApp>
        <IonReactRouter>
            <IonRouterOutlet>
                <Route exact path={HOME} component={Home} />
                <Route exact path={LOGIN} component={Login} />
                <Route exact path={MAIN_PAGE} component={MainPage} />
                <Route exact path={REGISTER} component={Register} />
            </IonRouterOutlet>
        </IonReactRouter>
    </IonApp>
);

export default App;
