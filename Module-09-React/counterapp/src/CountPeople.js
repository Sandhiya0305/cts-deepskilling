import React from 'react';

export default class CountPeople extends React.Component {
  constructor() {
    super();
    this.state = {
      entrycount: 0,
      exitcount: 0
    };
  }

  UpdateEntry() {
    this.setState((prevState) => {
      return { entrycount: prevState.entrycount + 1 };
    });
  }

  UpdateExit() {
    this.setState((prevState) => {
      return { exitcount: prevState.exitcount + 1 };
    });
  }

  render() {
    return (
      <div style={{ display: 'flex', justifyContent: 'center', marginTop: '220px', gap: '180px' }}>
        <div>
          <button
            type="button"
            onClick={() => this.UpdateEntry()}
            style={{ backgroundColor: '#b7f0b1', border: '1px solid #4f8f4f', padding: '2px 8px' }}
          >
            Login
          </button>
          <span> {this.state.entrycount} People Entered!!!</span>
        </div>
        <div>
          <button
            type="button"
            onClick={() => this.UpdateExit()}
            style={{ backgroundColor: '#b7f0b1', border: '1px solid #4f8f4f', padding: '2px 8px' }}
          >
            Exit
          </button>
          <span> {this.state.exitcount} People Left!!!</span>
        </div>
      </div>
    );
  }
}
