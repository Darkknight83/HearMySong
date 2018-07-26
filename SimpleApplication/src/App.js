import React, { Component} from 'react';
import { Route, Link, Switch } from 'react-router-dom'
import About from './Components/About/About.js';
import Help from './Components/Help/Help.js';
import Listen from './Components/Listen/Listen.js';
import './App.css';

class App extends Component {

  constructor() {
    super();

    this.state = {
      showMenu: false,
    };

    this.showMenu = this.showMenu.bind(this);
    this.closeMenu = this.closeMenu.bind(this);
  }

/*Trigger to open and close Dropdownmenu*/

  showMenu(event) {
    event.preventDefault();

    this.setState({
      showMenu: true
    }, () => {
      document.addEventListener('click', this.closeMenu);
    });
  }

  closeMenu() {
    this.setState({
      showMenu: false
    }, () => {
      document.removeEventListener('click', this.closeMenu);
    });
  }
  
 render() {
        return (
            <div className="App">          
            <link href='https://fonts.googleapis.com/css?family=Oswald' rel='stylesheet'/>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

{/*Div for the Navigation*/}

            <div className="topnav">

{/*HMS ------ Logo */}

            <div className="logo">
            <Link to="/"><img src={require('./images/hms-ongrey-w.png')} alt="" /></Link>
            </div>

{/*Adding the regular menu*/}

            <div className="menu">   
                <Link to="/">Listen</Link>
                <Link to="/help">Help</Link>
                <Link to="/about">About</Link>
            </div>       

{/*Adding the Dropdownmenu for small devices*/}

            <div className="dropdown">
            <a id="icon" onClick={this.showMenu}><i className="fa fa-bars"></i></a>
            {
          this.state.showMenu
            ? (
            <div className="dropmenu">   
            <Link to="/" >Listen</Link>
            <Link to="/help">Help</Link>
            <Link to="/about">About</Link>
            </div>   
            )
            : (
              null
            )
        }       
            </div>

            </div>

{/*Adding a div for the content*/}

  <div className = "content">
           <Switch>
              <Route exact path="/" component={Listen}/>
              <Route path="/help" component={Help}/>
              <Route path="/about" component={About}/>
          </Switch>
  </div>
</div>                                                        
        );
    }
}

export default App;