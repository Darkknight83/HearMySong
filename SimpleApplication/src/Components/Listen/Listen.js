import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import SpotifyWebApi from 'spotify-web-api-js';
import './Listen.css';
import Slider from 'react-rangeslider'
import 'react-rangeslider/lib/index.css'
import { Grid, Row, Col } from 'react-flexboxgrid';
import Room from './Room/Room.js';
import {DragDropContext, Droppable, Draggable} from 'react-beautiful-dnd';
import SongSearch from './Search/SongSearch.js';
import SimpleBar from 'simplebar-react';
import 'simplebar/dist/simplebar.min.css';
import ListofRooms from './ListofRooms/ListofRooms.js';

const spotifyApi = new SpotifyWebApi();
const registerUser = 'http://localhost:8080/hear-my-song-web/rest/user/register';

class RoomEntity {
    constructor(name, genre, owner, password , tags) {
        this.name = name;
        this.password = password;
        this.owner = owner;
        this.genre = genre;
        this.tags = tags;
        this.listeners = 0;
    }
}

export default class Listen extends Component {
    constructor(){
        super();

/*   GETTING BOTH TOKENS FROM URL  */

        const params = this.getHashParams();
        const token = params.access_token;
        const refresh_token = params.refresh_token;

/* SETTING ACCESTOKEN FOR THE API */

        if (token) {
            spotifyApi.setAccessToken(token);
        }

/*ARRAY OF ROOMS TO BE DISPLAYED IN LIST */

        let roomarray = [];

/* SOME INITIAL STATES */

        this.state = {
            token: token,
            refresh_token: refresh_token,
            loggedIn: token ? true : false,
            nowPlaying: { name: 'Name', artists: "Artists", albumArt: '' },
            isplaying: false,
            value: 50,
            rooms: roomarray,
            valueRoom: '',
            roomlist: 'Show Rooms',
            currentRoom: null
        }

        this.state.rooms.push(new RoomEntity("HMS - Mostplayed", "All", "hms", null, "Trending"));
        this.state.rooms.push(new RoomEntity("HMS - Rock", "Rock", "hms", null, "Headbang"));
        this.state.rooms.push(new RoomEntity("HMS - Pop", "Pop", 'hms', null, "chillin"));
        this.state.rooms.push(new RoomEntity("HMS - House", "House", 'hms', null, "Party"));
        this.state.rooms.push(new RoomEntity("HMS - Metal", "Metal", 'hms', null, "Screaming"));
        this.state.rooms.push(new RoomEntity("HMS - Funk", "Funk", 'hms', null, "GoodVibes"));
        this.state.rooms.push(new RoomEntity("HMS - Soul", "Soul", 'hms', null, "Relax"));
        this.state.rooms.push(new RoomEntity("HMS - RnB", "RnB", 'hms', null, "Clubbin"));
        this.state.rooms.push(new RoomEntity("HMS - Black", "Black", 'hms', null, "Clubbin"));
        this.state.rooms.push(new RoomEntity("HMS - Classic", "Classic", 'hms', null, "Relaxing"));
        this.state.rooms.push(new RoomEntity("HMS - Private", "All", 'hms', "admin", "Experimental"));

          this.setRoom = this.setRoom.bind(this);
          this.addRoom = this.addRoom.bind(this);

/*  TO MAKE VOLUME EQUAL WITH SPOTIFY VOLUME*/

        spotifyApi.setVolume(50, {});
        this.createUser();/* WRONG PLACE HERE*/


    }

/* WRITES A NEW USER TO OUR DATABASE */

  createUser = (url = registerUser, data = {
  "name": "Username",
  "service": 1,
  "accessToken": this.state.token,
  "refreshToken": this.state.refresh_token
}) => {
        return fetch(url, {
            method: "PUT",
            mode: "cors",
            cache: "no-cache",
            credentials: "omit",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
            redirect: "follow",
            referrer: "no-referrer",
            body: JSON.stringify(data),
        })
        .then(response => response.json()) // parses response to JSON
        .catch(error => console.error(`Fetch Error =\n`, error));
    };

/*  GET TOKENS FROM URL */

    getHashParams(){
        var hashParams = {};
        var e, r = /([^&;=]+)=?([^&;]*)/g,
            q = window.location.hash.substring(1);
        e = r.exec(q)
        while (e) {
            hashParams[e[1]] = decodeURIComponent(e[2]);
            e = r.exec(q);
        }
        return hashParams;
    }

/* A SLEEP USED TO LOWER THE AMOUNT OF API REQUESTS */

    sleep(milliseconds) {
        var start = new Date().getTime();
        for (var i = 0; i < 1e7; i++) {
            if ((new Date().getTime() - start) > milliseconds){
                break;
            }
        }
    }

/* SETTING CURRENT ROOM AS STATE FOR STATEBASED RENDERING */

    setRoom = (currentRoom) => {
      this.setState({currentRoom: currentRoom})
    }


    /* SETTING CURRENT ROOM AS STATE FOR STATEBASED RENDERING */

        addRoom(newRoom) {
          console.log("In addRoom:");
          console.log(this.state.rooms);
          this.setState({
            rooms: this.state.rooms.concat(newRoom)
          })
          console.log(newRoom);
          console.log(this.state.rooms);
          console.log("Out addRoom:");
        }


/* GETTING THE CURRENT PLAYBACK STATE FROM USER TO DISPLAY IN THE PLAYER */

    getPlaybackState(){
        this.sleep(70);
        spotifyApi.getMyCurrentPlaybackState()
            .then((response) => {
                this.setState({
                    nowPlaying: {
                        name: response.item.name,
                        artists: response.item.artists,
                        albumArt: response.item.album.images[0].url
                    },
                    volume: response.device.volume_percent,
                    isplaying: response.is_playing,
                });
            })
    }

/* API CALL TO PAUSE A SONG */

    pauseSong(){
        spotifyApi.pause({});
    }

/* API CALL TO PLAY A SONG */

    playSong(){
        spotifyApi.play({});
    }

/* HANDLER FOR THE VOLUMESLIDER */

    handleChange = value => {
        this.setState({
            value: value
        })
        spotifyApi.setVolume(value, {});
    };

/* TOGGLE THE VISIBILITY OF ROOMLIST FOR XS SCREENS */

    toggleRoomlist() {
        let div = document.getElementById('leftbar');
        div.style.display = div.style.display == "block" ? (div.style.display ="none", this.setState({
            roomlist: 'Show Rooms'}))  : (div.style.display = "block", this.setState({
            roomlist: 'Hide Rooms'}));
        let diva = document.getElementById('center');
        diva.style.display = div.style.display == "block" ? "none" : "block";
        let divb = document.getElementById('rightbar');
        divb.style.display = div.style.display == "block" ? "none" : "block";

    }

    render() {


      /*  SPOTIFY WEBPLAYBACK */

      window.onSpotifyWebPlaybackSDKReady = () => {
             const Spotify = window.Spotify;
            const token = this.state.token;
            const player = new Spotify.Player({
              name: 'Hear My Song',
              getOAuthToken: cb => { cb(token); }
            });

            // Error handling
            player.addListener('initialization_error', ({ message }) => { console.error(message); });
            player.addListener('authentication_error', ({ message }) => { console.error(message); });
            player.addListener('account_error', ({ message }) => { console.error(message); });
            player.addListener('playback_error', ({ message }) => { console.error(message); });

            // Playback status updates
            player.addListener('player_state_changed', state => { console.log(state); });

            // Ready
            player.addListener('ready', ({ device_id }) => {
              console.log('Ready with Device ID', device_id);
            });

            // Not Ready
            player.addListener('not_ready', ({ device_id }) => {
              console.log('Device ID has gone offline', device_id);
            });

            // Connect to the player!
            player.connect();
          };

/* UPDATE PLAYBACKSTATE PERMANENTLY */

        this.getPlaybackState();

/* VOLUMESLIDER VALUE */

        const { value } = this.state

/* ADDS THE ARTISTS OF THE SONG FROM CURRENT PLAYBACK */

        const { artists } = this.state.nowPlaying
        let text = ""
        let i
        for(i = 0; i<artists.length-1; i++){
            text += artists[i].name + ", "
        }
        text += artists[i].name

/* GET THE ROOMARRAY FROM STATE AND CREATES DIVS TO DISPLAY*/




        return (

            <div id="container">
            {/* IF LOGGED IN STATE = TRUE, DISPLAY THIS*/}
                {this.state.loggedIn &&
                <div id="loged" className="loged-display">
                    <div id="content" class="row">
                        <div id="showrooms" class="col-xs-12 col-sm-12">
                            <button onClick={() => this.toggleRoomlist()}>{this.state.roomlist}</button>
                        </div>
                        <div id="leftbar" class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                                <ListofRooms
                                setRoom={this.setRoom}
                                rooms={this.state.rooms}
                                />
                                {console.log("In Listen")}
                                {console.log(this.state.rooms)}
                                {console.log("Out Listen")}
                        </div>
                        <div id="center" class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
                            <Room
                            rooms={this.state.rooms}
                            addRoom={this.addRoom}
                            setRoom={this.setRoom}
                            currentRoom={this.state.currentRoom}
                            />
                        </div>
                        <div id="rightbar" class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
                        {this.state.currentRoom != null &&
                            <SongSearch/>
                          }
                        </div>
                    </div>
                    {/* DISPLAY FOOTER WHILE NOT IN A ROOM */}
                    {this.state.currentRoom == null &&
                    <div id="footer" className="footer">
                    </div>
                    }
                    {/* DISPLAY PLAYER WHILE IN A ROOM */}
                    {this.state.currentRoom != null &&
                    <div id="player" className="player">
                        <div id="album-img">
                            <img src={this.state.nowPlaying.albumArt} style={{ height: 55 }}/>
                        </div>
                        <div id="song-name">
                            <div id="onlyname">
                                <p>{ this.state.nowPlaying.name }</p>
                            </div>
                            <div id="song-artist">
                                { text }
                            </div>
                        </div>
                        <div id="playpause">
                            {!this.state.isplaying &&
                            <img src={require('./icons/playbutton.png')} onClick={() => this.playSong()} />
                            }
                            {this.state.isplaying &&
                            <img src={require('./icons/pausebutton.png')} onClick={() => this.pauseSong()} />
                            }
                        </div>
                        <div id ="slider" className='slider'>
                            <div className='slider orientation-reversed'>
                                <div className='slider-group'>
                                    <div className='slider-horizontal'>
                                        <Slider
                                            min={0}
                                            max={100}
                                            value={value}
                                            orientation='horizontal'
                                            onChange={this.handleChange}
                                        />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    }
                </div>
                }
              {/* SHOW LOGIN BUTTON IF NOT LOGGED IN */}
                {!this.state.loggedIn &&
                <div id="unloged" className="unloged-display">
                    <div>
                        <p>This Website is using the Spotify API.
                            You need a Spotify-Account to listen to Music.</p>
                    </div>
                    <div>
                        <a href='http://localhost:8888/login'> Login with Spotify </a>
                    </div>
                </div>
                }

            </div>
        );
    }
}
