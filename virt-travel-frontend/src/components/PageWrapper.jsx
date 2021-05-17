import React, { Component } from 'react'
import Header from './Header'
import GlobalStyles from './styles/GlobalStyles'
import {ContentDiv} from './styles/ContentDiv'

export default class PageWrapper extends Component {
    render(props) {
        return (
            <ContentDiv>
                <GlobalStyles />
                <Header />
                {this.props.children}
            </ContentDiv>
        )
    }
}
