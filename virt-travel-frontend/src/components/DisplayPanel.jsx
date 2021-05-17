import React, { Component } from 'react'
import styled from 'styled-components'


const LeftPanel = styled.div`
    background-color: var(--cornflower);
    width: 80%;
`

export default class DisplayPanel extends Component {
    render(props) {
        return (
            <LeftPanel>
               {this.props.children} 
            </LeftPanel>
        )
    }
}
