import React, {
    Component
} from 'react';
import {
    Grid,
    Row,
    Col
} from 'react-flexbox-grid';
import './SongView.css';

export default class SongView extends Component {

    //Sollte Infos über Song kriegen
    constructor(props) {
        super(props);
    }

    // Sind schon in Row drinne
    render() {
        return (
            <span className="songview">
                <Grid fluid>
                    <Row around="md">
                    <Col md={1}>
                        <i className="fa fa-music"></i>
                    </Col>
                    <Col md={8}>
                        { this.props.song.songname }
                    </Col>
                    <Col md={1}>
                        <i className="fa fa-ellipsis-h" aria-hidden="true"></i>
                    </Col>
                    <Col md={1}>
                        { this.props.song.length }
                    </Col>
                    </Row>
                </Grid>
            </span>

        );
    }
}
