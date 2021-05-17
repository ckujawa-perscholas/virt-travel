import React, { Component } from 'react'
import styled from 'styled-components'
import Header from './Header.jsx'
import GlobalStyles from './styles/GlobalStyles'
import {ContentDiv} from './styles/ContentDiv'

const FlexPanel = styled.div`
    display: flex;
`
export default class PageWrapper extends Component {
    render(props) {
        return (
            <ContentDiv>
                <GlobalStyles />
                <Header>
                    <h1>Virtual Travel</h1>
                </Header>
                <FlexPanel>
                    {this.props.children}
                </FlexPanel>
            </ContentDiv>
        )
    }
}
