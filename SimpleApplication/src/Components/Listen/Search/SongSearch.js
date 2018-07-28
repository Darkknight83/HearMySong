import React, {
    Component
} from 'react';
import {
    Grid,
    Row,
    Col
} from 'react-flexbox-grid';
import { DragDropContext, Droppable, Draggable } from 'react-beautiful-dnd';
import Autocomplete from 'react-autocomplete';
import { SearchBar } from 'react-search-bar';
import './SongSearch.css'

class Song {
    constructor(songname, artist, album, length) {
        this.songname = songname;
        this.artist = artist;
        this.album = album;
        this.length = length;
    }
}

// fake data generator
const getItems = count =>
  Array.from({ length: count }, (v, k) => k).map(k => ({
    id: `${k}`,
    song: new Song(`${k} - Song`, 'Testartist', 'Testalbum', Math.random() * 500 / 60),
  }));


const grid = 8;

const getItemStyle = (isDragging, draggableStyle) => ({
  // some basic styles to make the items look a bit nicer
  userSelect: 'none',
  padding: grid * 2,
  margin: `0 0 ${grid}px 0`,

  // change background colour if dragging
  background: isDragging ? 'lightgrey' : 'grey',

  // styles we need to apply on draggables
  ...draggableStyle,
});

const getListStyle = isDraggingOver => ({
  background: isDraggingOver ? 'rgba(62, 62, 62, 1)' : 'lightgrey',
  padding: grid,
  width: 250,

});

const reorder = (list, startIndex, endIndex) => {
  const result = Array.from(list);
  const [removed] = result.splice(startIndex, 1);
  result.splice(endIndex, 0, removed);

  return result;
};




export default class SongSearch extends Component {


    constructor(props) {
        super(props);
        this.state = {
            items: getItems(5),
            searchPre: '',
        }
    }

    addItemToRoom(item) {
        console.log(' ' + item.song.songname + ' added');
        //TODO
    }

    searchTerm() {
        //TODO
    }

    render() {
        return (
            <div>
           -<Grid fluid>
            <Col>
            <Row>
                <Autocomplete
                    getItemValue={(item) => item}
                    items={this.state.items.map(el => el.song.songname)}
                    renderItem={(item, isHighlighted) =>
                        <div style={{ background: isHighlighted ? 'lightgray' : 'white', color: 'black' }}>
                            {item}
                        </div>
                    }
                    value={this.state.searchPre}
                    onChange={(e) => this.state.searchPre = e.target.value}
                    onSelect={(val) => {
                        const filteredItems = this.state.items.filter(el => el.song.songname.localeCompare(val) === 0);
                        this.setState({items: filteredItems})
                    }}
            />
            <span onClick={this.searchTerm()} className="fa-stack fa-lg">
                <i className="fa fa-square fa-stack-2x"></i>
                <i className="fa fa-search fa-stack-1x fa-inverse" style= {{color: '#00B8FF'}}></i>
            </span>
            </Row>
            </Col>
            </Grid>
                   
            <Droppable droppableId="droppable">
            {(provided, snapshot) => (
                <div
                    ref={provided.innerRef}
                    style={getListStyle(snapshot.isDraggingOver)}
                >
                {this.state.items.map((item, index) => (
                    <Draggable key={item.id} draggableId={item.id} index={index}>
                    {(provided, snapshot) => (
                        <div
                            ref={provided.innerRef}
                            {...provided.draggableProps}
                            {...provided.dragHandleProps}
                            style={getItemStyle(
                                snapshot.isDragging,
                                provided.draggableProps.style
                            )}
                            >
                            <div id="flex-container">
                                <span className="song-name" id="flex">{ item.song.songname }
                                </span>
                                <span className="song-length" id="raw"> { item.song.length.toFixed(2) }m
                                </span>
                                <span onClick={this.addItemToRoom(item)}
                                 className="add-button" id="raw"><button className="btn btn-mini"><i className="fa fa-plus"></i></button></span>
                            </div>
                        </div>
                    )}
                    </Draggable>
                ))}
                {provided.placeholder}
                </div>
            )}
            </Droppable>
            </div>
        );
    }

}

