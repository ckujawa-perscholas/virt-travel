package com.ckujawa.demo.controller;

import com.ckujawa.demo.model.Cart;
import com.ckujawa.demo.model.Trip;
import com.ckujawa.demo.repos.ICartRepository;
import com.ckujawa.demo.repos.ITripRepository;
import com.ckujawa.demo.util.CartActions;
import com.ckujawa.demo.util.NoSuchOperationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * a class to provide cart related services
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/cart")
@Api(value = "Calls to manage the cart and cart contents.")
public class CartController {

    @Autowired
    private ICartRepository cartRepo;

    @Autowired
    private ITripRepository tripRepo;

    /**
     * get a specific cart
     * @param cartId The id of the cart
     * @return a ResponseEntity
     */
    @ApiOperation("Get a cart using it's id.")
    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCardById(@PathVariable Long cartId){
        log.debug("Getting cart with id <" + cartId + ">.");
       try {
           Optional<Cart> cartOption = cartRepo.findById(cartId);
           if (cartOption.isPresent()){
               log.debug("Found cart with id <" + cartId + ">.");
               return new ResponseEntity<>(cartOption.get(), HttpStatus.OK);
           } else {
               log.debug("Did not find cart with id <" + cartId + ">.");
               return new ResponseEntity<>("Unable to find cart with id <" + cartId + ">.", HttpStatus.NO_CONTENT);
           }
       } catch (Exception e){
           log.error("An error occurred with retrieving cart with id <" + cartId + ">: " + e.getMessage(), e);
           return new ResponseEntity<>("An error occurred when retrieving your cart.", HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    /**
     * Create a new cart
     *
     * @return the newly created cart
     */
    @ApiOperation("Create a new cart object based on the object passed in the request body.")
    @PostMapping
    public ResponseEntity<?> createCart(){
        log.debug("Creating a new cart...");
        try {
            return new ResponseEntity<>(cartRepo.save(new Cart()), HttpStatus.OK);
        } catch (Exception e){
            log.error("An error occurred when creating a new cart: " + e.getMessage(), e);
            return new ResponseEntity<>("We were unable to create a new cart.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create a new cart
     * @param tripId the id of a trip to add
     * @return the newly created cart
     */
    @ApiOperation("Create a new cart object and add the trip to the cart.")
    @PostMapping(value = "/{tripId}")
    public ResponseEntity<?> createCart(@PathVariable Long tripId){
        log.debug("Creating a new cart...");
        try {
            Optional<Trip> tripOptional = tripRepo.findById(tripId);
            if (tripOptional.isPresent()) {
                Cart cart = new Cart();
                cart.addTrip(tripOptional.get());
                return new ResponseEntity<>(cartRepo.save(cart), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Unable to find a trip with id <" + tripId + ">.", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e){
            log.error("An error occurred when creating a new cart: " + e.getMessage(), e);
            return new ResponseEntity<>("We were unable to create a new cart.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Add or remove a trip from the cart...
     *
     * @param cartId the id of the cart
     * @param action an appropriate Actions
     * @param tripId the id of the trip to take cation on
     * @return the cart
     */
    @ApiOperation("Add or remove a trip from the cart. Path variables are cartId, Action (ADD/REMOVE), and tripId")
    @PutMapping("/{cartId}/{action}/{tripId}")
    public ResponseEntity<?> modifyCart(@PathVariable Long cartId, @PathVariable CartActions action, @PathVariable Long tripId){
        log.debug("Updating cart <" + cartId + "> with action <" + action.toString() + "> and trip <" + tripId + ">.");
        try{
            Optional<Cart> cartOptional = cartRepo.findById(cartId);
            if (cartOptional.isPresent()) {
               Cart cart = updateCartWithTrip(cartOptional.get(), action, tripId);
               return new ResponseEntity<>(cartRepo.save(cart), HttpStatus.OK);
            } else {
                log.debug("No cart with id <" + cartId + "> was found.");
                return new ResponseEntity<>("Unable to find cart with id <" + cartId + ">.", HttpStatus.NO_CONTENT);
            }
        } catch(NoSuchOperationException e){
            log.error("An invalid operation was attempted: " + action, e);
            return new ResponseEntity<>("Valid actions are ADD and REMOVE. Please try again.", HttpStatus.NOT_ACCEPTABLE);
        }
        catch (Exception e){
            log.error("An exception occurred when updating the cart: " + e.getMessage(), e);
            return new ResponseEntity<>("An error occurred when updating your cart. Please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update the cart with the trip according to the action.
     * @param cart the cart
     * @param action a CartActions to take on the cart
     * @param tripId the id of the trip to take the appropriate action with
     * @return the updated cart
     */
    private Cart updateCartWithTrip(Cart cart, CartActions action, Long tripId) throws NoSuchOperationException {
        switch (action){
            case ADD:
                Optional<Trip> tripOptional = tripRepo.findById(tripId);
                List<Trip> trips = cart.getCartContents();
                if (tripOptional.isPresent() && !trips.contains(tripOptional.get())){
                    cart.addTrip(tripOptional.get());
                    return cartRepo.save(cart);
                }
                return cart;
            case REMOVE:
                cart.removeTrip(tripId);
                return cartRepo.save(cart);
            default:
                throw new NoSuchOperationException(action + " is not a valid operation.");
        }
    }
}
