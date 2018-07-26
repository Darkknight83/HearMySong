import React, {
    Component
} from 'react';
import {
    Grid,
    Row,
    Col
} from 'react-flexbox-grid';
import SongSearch from './Search/SongSearch.js';
import Room from './Room/Room.js';
import './Listen.css';


export default class Listen extends Component {

    render() {
        return ( 
        <div className = "listen" >
            <Grid fluid>
                <Row>
                    <Col xs = {
                        12
                    }
                    md = {
                        5
                    }>
                     <SongSearch / >
                    </Col> 
                    <Col xs = {
                        12
                    }
                    md = {
                        7
                    }>
                     <Room/>
                    </Col> 
                </Row> 
            </Grid> 
        </div>
        )
    }
}
