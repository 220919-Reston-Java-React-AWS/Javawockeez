import { productModel } from "../../../models/productModel";
import { Link } from 'react-router-dom'

import Col from 'react-bootstrap/Col';
import Card from 'react-bootstrap/Card';
import Image from 'react-bootstrap/Image';
import { Row } from "react-bootstrap";

function MainProductBox(props: productModel){
    const styles = {
        container: {
          fontSize: "20px",
        },
      };

    return (
        // Bread Card 
        <Col sm="12" md="6" xl="4" className="mt-5">
            {/* <Link to={"#"} className="text-decoration-none text-dark text-left"> */}
                <Card className="p-4 background-form">
                    <Row>
                        <Col>
                            {/* Product Image */}
                            <Image src={props.imagePath} rounded fluid={true} id="card-image"/>
                        </Col>
                        <Col>
                            {/* Product text */}
                            <div className="text-truncate md-4">
                                <h5 className="text-wrap">{props.name}</h5>
                                <p>{props.brand}</p>
                                <p style={styles.container}>${props.price}</p>
                                <p className="text-wrap">Weight: {props.weight} oz</p>
                            </div>
                        </Col>
                    </Row>
                </Card>
            {/* </Link> */}
        </Col>
    )
}
export default MainProductBox;
