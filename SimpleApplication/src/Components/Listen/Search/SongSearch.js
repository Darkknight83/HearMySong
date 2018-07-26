import React, {
    Component
} from 'react';
import {
    Grid,
    Row,
    Col
} from 'react-flexbox-grid';
import Autocomplete from 'react-autocomplete';
import SongView from './SongView.js';

class Song {
    constructor(songname, artist, album, length) {
        this.songname = songname;
        this.artist = artist;
        this.album = album;
        this.length = length;
    }
}


export default class SongSearch extends Component {


    constructor(props) {
        super(props);
        this.state = {
            searchPre: '',
            songs: [new Song('Testsong', 'Testartist', 'Testalbum', 3.12),
             new Song('2Testsong', 'Testartist', 'Testalbum', 5.11),
             new Song('Testdiesdas', '2Testartist', '2Testalbum', 1.51)
            ]
        }
    }

    render() {
        return (
            <Grid fluid>
            <Col>
            <Row>
                <Autocomplete
                    getItemValue={(item) => item.label}
                    items={[
                        { label: 'apple' },
                        { label: 'banana' },
                        { label: 'pear' }
                    ]}
                    renderItem={(item, isHighlighted) =>
                        <div style={{ background: isHighlighted ? 'lightgray' : 'white', color: 'black' }}>
                            {item.label}
                        </div>
                    }
                    value={this.state.searchPre}
                    onChange={(e) => this.state.searchPre = e.target.value}
                    onSelect={(val) => val.value = val}
            />
            <span className="fa-stack fa-lg">
                <i className="fa fa-square fa-stack-2x"></i>
                <i className="fa fa-search fa-stack-1x fa-inverse" style= {{color: '#00B8FF'}}></i>
            </span>
            </Row>
            { this.state.songs.map(el => <Row> <SongView song={el}/> </Row>) }
            </Col>
            </Grid>
        );
    }

}

