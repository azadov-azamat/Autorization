import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import RegisterJs from "./components/pages/RegisterPage";
import LoginJs from "./components/pages/LoginPage";
import NotFound from "./components/pages/NotFound";


function App() {
  return (
      <>
          <Router>
              <Switch>
                  <Route  path='/' component={RegisterJs}/>
                  <Route  path='/signIn' component={LoginJs}/>
                  <Route  component={NotFound}/>
              </Switch>
          </Router>
      </>
  );
}

export default App;
