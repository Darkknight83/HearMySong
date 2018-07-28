import React, {
    Component
} from 'react';
import {DragDropContext, Droppable, Draggable} from 'react-beautiful-dnd';
import './Room.css'
import  ReactModal  from 'react-modal';

class RoomEntity {
    constructor(name, genre, owner, password , tags) {
        this.name = name;
        this.password = password;
        this.owner = owner;
        this.genre = genre;
        this.tags = tags;
    }
}

const creationDialogStyles = {
    overlay: {
        backgroundColor: 'rgba(62, 62, 62, 0.8)',
    },
    content : {
        background            : "linear-gradient(lightblue, #00B8FF)",
        color                 : 'black',
        top                   : '50%',
        left                  : '50%',
        right                 : 'auto',
        bottom                : 'auto',
        marginRight           : '-50%',
        transform             : 'translate(-50%, -50%)'
    }
};


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

ReactModal.setAppElement('#root');

export default class Room extends Component {

    constructor() {
        super();
        this.state = {
            creationDialogOpen: false,
            room: null,
            items: []
        };


        this.contentRef = React.createRef();
        this.openCreationDialog = this.openCreationDialog.bind(this);
        this.closeCreationDialog = this.closeCreationDialog.bind(this);
    }


    createRoom() {
        const form = this.contentRef.childNodes[1];
        const roomName =    form.
                            childNodes[0].      // fieldset
                            childNodes[0].      // first input (name)
                            value.
                            toString();

        const genre =    form.
                            childNodes[0].      // fieldset
                            childNodes[3].      // first input (name)
                            value.
                            toString();

        const password =  form.
                            childNodes[2].      // fieldset
                            childNodes[2].      // first input (name)
                            value.
                            toString();

        const tags =    form.
                            childNodes[2].      // fieldset
                            childNodes[4].      // first input (name)
                            value.
                            split(',').
                            map(tag => tag.trim());

        this.setState({room: new RoomEntity(roomName, genre, 'ownerPlaceholder', password, tags)});
        this.setState({creationDialogOpen: false});
        console.log(new RoomEntity(roomName, genre, 'ownerPlaceholder', password, tags));
    }

    leaveRoom() {
        this.setState({room: null})
    }

    openCreationDialog() {
        this.setState({creationDialogOpen: true})
    }

    closeCreationDialog() {
        this.setState({creationDialogOpen: false});
    }

    render() {

        var genreList = ['Rock','Pop','House','Metal','Funk','Soul','RNB','Black','Classic']

        const divClassName = 'room';
        if (this.state.room == null) {
            return (
                <div className={divClassName}>
                    <button className="btn btn-info" onClick={this.openCreationDialog}
                            type="button" name="Create"
                            style={{color: 'black', backgroundColor: 'silver'}}>Create New Room
                    </button>
                    <ReactModal
                        isOpen={this.state.creationDialogOpen}
                        onRequestClose={this.closeCreationDialog}
                        style={creationDialogStyles}
                        contentLabel="Example Modal"
                        contentRef={node => this.contentRef = node}
                    >
                        <h3>Create Room</h3>
                        <form>
                            <fieldset>
                                <input type="text" name="roomname"
                                       placeholder="roomname"
                                       autoFocus={true}/><br/>
                                <span> Genre: </span>
                                <select id = "genres">
                                    { genreList.map(genre => <option key={genre} value={genre}>{genre}</option>)}
                                </select>
                            </fieldset>
                            <hr/>
                            <fieldset>
                                <label>
                                    <input id="checkbox-a" name="checkbox-a" type="checkbox" defaultChecked={true}/> Publicly visible
                                </label><br/>
                                <input type="text" name="password"
                                       placeholder="password" /><br/>
                                <input type="text" name="tags"
                                       placeholder="Tag 1,Tag 2,Tag 3"/>
                            </fieldset>
                            <hr/>
                            <fieldset>
                                <button className="btn btn-success" onClick={() => this.createRoom()}>Create</button>
                                <button className="btn btn-danger" onClick={() => this.closeCreationDialog}>Close</button>
                            </fieldset>
                        </form>
                    </ReactModal>
                </div>

            );
        } else {
            return (
                <div className={divClassName}>
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
                                                    <span className="song-info" id="flex">{item.song.songname}</span>
                                                    <span contentEditable='true' onClick={this.addItemToRoom(item)}
                                                          className="add-button" id="raw"><i className="fa fa-plus"></i></span>
                                                </div>
                                            </div>
                                        )}
                                    </Draggable>
                                ))}
                                {provided.placeholder}
                            </div>
                        )}
                    </Droppable>
                    <button onClick={() => this.leaveRoom()} type="button" name="Leave" style={{color: 'black'}}>Leave
                    </button>
                </div>
            );
        }
    }
}