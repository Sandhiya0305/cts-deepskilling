import React from 'react';

export default class Cart extends React.Component {
  render() {
    return (
      <table
        border="1"
        cellPadding="4"
        cellSpacing="0"
        style={{ margin: '0 auto', borderCollapse: 'collapse', color: 'mediumseagreen' }}
      >
        <thead>
          <tr>
            <th style={{ width: '120px' }}>Name</th>
            <th style={{ width: '70px' }}>Price</th>
          </tr>
        </thead>
        <tbody>
          {this.props.item.map((item, index) => (
            <tr key={index}>
              <td style={{ textAlign: 'center' }}>{item.Itemname}</td>
              <td style={{ textAlign: 'center' }}>{item.Price}</td>
            </tr>
          ))}
        </tbody>
      </table>
    );
  }
}
