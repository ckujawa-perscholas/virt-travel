import React, { Component } from 'react'
import CartDisplay from '../components/CartDisplay'
import DisplayPanel from '../components/DisplayPanel'
import PageWrapper from '../components/PageWrapper'



export default class Main extends Component {

    constructor(props) {
        super(props)
        this.state = {
            trips: []
        }
    }

    async componentDidMount() {
        const rawTrips = await fetch("http://localhost:8080/api/trips")
        const trips = await rawTrips.json();

        console.log(trips);


        this.setState({
            trips: trips
        })
    }
    render() {
        return (
            <PageWrapper>
                <DisplayPanel>
                    <p>Trip Info Goes Here</p>
                </DisplayPanel>
                <CartDisplay>
                    Cart
                </CartDisplay>
            </PageWrapper>
        );
    }
}
