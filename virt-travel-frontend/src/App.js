
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import TripDetail from './pages/TripDetail'
import Main from "./pages/Main"

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/trip/:tripId">
          <TripDetail/>
        </Route>
        <Route path="/">
          <Main/>
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
