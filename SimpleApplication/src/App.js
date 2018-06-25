import React, {
  Component
} from 'react';
import uuid from 'uuid';
import $ from 'jquery';
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
            <div className="topnav">
            <div className="logo">
            <a href="#"><img src={require('./images/hms-ongrey-w.png')}/></a>
            </div>

            <div id="menuitems" className="menu">   
            <a href="#">Listen</a>    
            <a href="#">Help</a>
            <a href="#">About</a>
            </div>          

            <div className="dropdown">
            <a id="icon" onClick={this.showMenu}><i className="fa fa-bars"></i></a>
            {
          this.state.showMenu
            ? (
            <div className="dropmenu">   
            <a href="#">Listen</a>
            <a href="#">Help</a> 
            <a href="#">About</a>
            </div>   
            )
            : (
              null
            )
        }       
            </div>

            </div>
  <div className = "content">
  Test
  </div>
</div>       
           
        );
    }
}

export default App;