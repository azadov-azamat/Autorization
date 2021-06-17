import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import RegisterJs from "./components/pages/RegisterPage";
import NotFound from "./components/pages/NotFound";
import Pages from "./components/pages/Pages";
import './components/style/main.scss';

function App() {
  return (
      <>
          <Router>
              <Switch>
                  <Route exact path='/' component={Pages}/>
                  <Route path='/signUp' component={RegisterJs}/>
                  <Route component={NotFound}/>
              </Switch>
          </Router>
      </>
  );
}

export default App;
