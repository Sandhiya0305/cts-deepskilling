import React from 'react';

class ComplaintRegister extends React.Component {
  constructor() {
    super();
    this.state = {
      ename: 'Shrivalli',
      complaint: 'Keyboard not working',
      NumberHolder: Math.floor(Math.random() * 100)
    };
  }

  handleChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  }

  handleSubmit(event) {
    var msg = 'Thanks ' + this.state.ename + '\nYour Complaint was Submitted\nTransaction Id is: ' + this.state.NumberHolder;
    alert(msg);
    event.preventDefault();
  }

  render() {
    return (
      <div style={{ textAlign: 'center', marginTop: '90px' }}>
        <h1 style={{ color: 'red' }}>Register your complaints here!!!</h1>
        <form onSubmit={(event) => this.handleSubmit(event)}>
          <table style={{ margin: '0 auto' }}>
            <tbody>
              <tr>
                <td>Name:</td>
                <td>
                  <input
                    type="text"
                    name="ename"
                    value={this.state.ename}
                    onChange={(event) => this.handleChange(event)}
                  />
                </td>
              </tr>
              <tr>
                <td>Complaint:</td>
                <td>
                  <textarea
                    name="complaint"
                    value={this.state.complaint}
                    onChange={(event) => this.handleChange(event)}
                  />
                </td>
              </tr>
              <tr>
                <td></td>
                <td>
                  <button type="button" onClick={(event) => this.handleSubmit(event)}>Submit</button>
                </td>
              </tr>
            </tbody>
          </table>
        </form>
      </div>
    );
  }
}

export default ComplaintRegister;
