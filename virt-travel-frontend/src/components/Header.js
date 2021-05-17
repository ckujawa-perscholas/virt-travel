import React, { Component } from 'react'
import styled from 'styled-components'

const HeaderStyle = styled.header`
    width: 100%;
    height: 100px;
    text-align: center;
`

export default class Header extends Component {
    render() {
        return (
            <HeaderStyle>
                {this.props.children}
            </HeaderStyle>
        )
    }
}
