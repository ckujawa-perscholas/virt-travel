import React, { Component } from 'react'
import PageWrapper from '../components/PageWrapper'



export default class Main extends Component {

    constructor(props) {
        super(props)
        this.state = {
            trips: [],
            cart: {}
        }
    }

    async componentDidMount() {
        const rawTrips = await fetch("http://localhost:8080/api/trips")
        const trips = await rawTrips.json();

        const options = {method: 'POST', headers: {'Content-Type': 'application/json'}}
        const rawCart = await fetch('http://localhost:8080/api/cart/', options);
        const cart = await rawCart.json();

        this.setState({trips: trips, cart: cart})
    }
    render() {
        return <PageWrapper>Hello!</PageWrapper>;
    }
}
