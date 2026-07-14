import React from 'react';
import Cart from './Cart';

export default class OnlineShopping extends React.Component {
  render() {
    const cartInfo = [
      { Itemname: 'Laptop', Price: 80000 },
      { Itemname: 'TV', Price: 120000 },
      { Itemname: 'Washing Machine', Price: 50000 },
      { Itemname: 'Mobile', Price: 30000 },
      { Itemname: 'Fridge', Price: 70000 }
    ];

    return (
      <div style={{ textAlign: 'center', marginTop: '80px' }}>
        <h1 style={{ color: 'green', fontSize: '32px', marginBottom: '25px' }}>Items Ordered :</h1>
        <Cart item={cartInfo} />
      </div>
    );
  }
}
