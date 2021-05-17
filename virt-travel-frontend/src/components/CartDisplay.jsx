import React, { Component } from 'react'
import styled from 'styled-components'

const CartPanel = styled.div`
    width: 20%;
    background-color: var(--cornflower);
    display: flex;
    flex-direction: column;

    h1 {
        font-size: 32px;
        margin: 0 0 .5rem 0;
        padding: .2rem;
        text-align: center;
        width: 100%
    }
`

const PriceDiv = styled.div`
    font-weight: bold;
    text-align: right;
    width: 100%
`


export default class CartDisplay extends Component {
    render(props) {
        return (
            <CartPanel>
                <h1>Cart</h1>
                {this.props.children}
                <p>Cart Item</p>
                <p>Cart Item</p>
                <p>Cart Item</p>
                <p>Cart Item</p>
                <PriceDiv>$100,000</PriceDiv>
            </CartPanel>
        )
    }
}
