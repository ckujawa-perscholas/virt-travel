import React, {useEffect} from 'react'
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import TripDetail from './pages/TripDetail'
import Main from "./pages/Main"
import { useStateWithPromise } from './hooks/useStateWithPromise';



//functional component using hooks.

function App() {
  const [cart, setCart] = useStateWithPromise({})

  const createCart = async () => {
    const options = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
    };
    const rawCart = await fetch("http://localhost:8080/api/cart/", options);
    const cart = await rawCart.json();

    setCart(cart)
  } 

  useEffect(() => {
    createCart()
  },[])

  return (
    <Router>
      <Switch>
        <Route path="/trip/:tripId">
          <TripDetail cart={cart} setCart={setCart} />
        </Route>
        <Route path="/">
          <Main cart={cart} setCart={setCart} />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
