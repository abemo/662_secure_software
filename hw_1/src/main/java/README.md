The code for 662 Secure Software Development HW 1, shopping cart assignment

[text](https://cs.lmu.edu/~ray/classes/ssd/assignment/1/)

Specification

- [✅] Define a new type (or class) for shopping carts, in which a cart must
  - [✅] (1) have its own id
  - [✅] (2) hold the id of the customer that the cart belongs to
  - [✅] (3) the items in the cart along with their quantities
- [✅] Provide a way for users to query the id, customer id, and items from the cart
- [✅] Ensure that no one can change the cart indirectly by modifying the values (ids, items, etc.) you provided them.
- [✅] Provide a way for users to add, update, and remove items.
- [✅] Ensure the id and customer id of the cart can never be changed.
- [✅] Provide a method to get the total cost of the items in the cart.
- [✅] Store shopping cart ids as uuid4s.
- [❌] Require customer ids to have the following form: 3 letters, 5 numbers, 2 letters, a dash, and finally an A or a Q.
  - update letter to allow any unicode letter
- [✅] Ensure negative quantities for any item in the cart can never appear.
- [❌] Check for upper bounds on every quantity as well
- [✅] Make sure items added to the cart are in some kind of catalog, and are not just made up (hack in) by an adversary.
- [✅] Ensure item names are length-bounded and the characters in the names restricted.
  - allow unicode letters in item names
- [✅] Employ immutability everywhere that it makes sense to. (Immutability should always be the default.)
- [✅] Maintain integrity at all times. (Do not allow bad data to creep in, ever. If something is wrong, throw an exception)
- [✅] Install a security linter, such as SonarCube
- [❌] 3 points for giving the link so I can find your code
- [❌] 10 points that the tests can be found and run, and that they all pass and have good coverage
- [ ] 5 points that the whole file was formatted well
- [ ] 3 points for good, meaningful names
- [ ] 7 points for type and bounds validation
- [ ] 7 points for using immutability or defensive copying so as to not allow malicious updates
- [ ] 8 points are awarded for checking off every requirement according to the specification
