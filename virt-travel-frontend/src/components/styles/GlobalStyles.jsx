import { createGlobalStyle } from 'styled-components'
import { normalize } from 'styled-normalize'
import font from '../../assets/roboto-v20-latin-regular.woff'

const GlobalStyles = createGlobalStyle`
    ${normalize}


    @font-face {
        font-family: Roboto;
        src: url(${font});
    }

    :root {
        --black: #020211;
        --white: #f1ece5;
        --timberwolf: #DDDBD3;
        --cornflower: #92D0E9;
    }

    html: {
        height: 100vh;
        width: 100vw;
        padding: 20px;
        margin: 0;
        box-sizing: border-box;
    }

    body: {
        font-family: Roboto, Roboto-apple-system, BlinkMacSystemFont, 'Segoe UI', Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
        color: var(--black);
        font-size: 1em;
    }

    h1 {
        font-size: 3em;
        margin: .5em;
        padding: .5em;
    }

    h2 {
        font-size: .25em;
        margin: .3em;
        padding: .3em;
    }

    h3 {
        font-size: 2em;
        margin: .25em;
        padding: .25em;
    }

    h4 {
        font-size: 1.25em;
        margin: .1em;
        padding: .1em;
    }

    h5{
        font-size: .75em;
        margin: 0;
        padding: .1em;
    }
`;

export default GlobalStyles;